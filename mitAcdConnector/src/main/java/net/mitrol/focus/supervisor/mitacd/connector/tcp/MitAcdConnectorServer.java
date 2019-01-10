package net.mitrol.focus.supervisor.mitacd.connector.tcp;

import net.mitrol.utils.ExecutorBuilder;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;

/**
 * @author ladassus
 */
public abstract class MitAcdConnectorServer {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(MitAcdConnectorServer.class);

    private ExecutorService executor;

    public void init (int port) {
        ServerSocket server = null;
        Socket socket = null;
        this.executor =  ExecutorBuilder.buildNewSingleExecutorService("MitAcd");
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            logger.error(e);
        }
        while (true) {
            try {
                socket = server.accept();
            } catch (IOException e) {
                logger.error(e);
            }
            executor.submit(new MitAcdConnectorServer.MitAcdClientConn(socket));
        }
    }

    public abstract void onMitAcdMessageReceived(String message);

    private class MitAcdClientConn implements Runnable {
        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        private ConnectionState state = ConnectionState.Disconnected;
        String receivedMessage;

        public MitAcdClientConn (Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            if (socket.isConnected()) {
                state = ConnectionState.Connected;
                disposeDataInputStream();
                disposeDataOutPutStream();
                try {
                    checkInitDataInputStream();
                    checkInitDataOutPutStream();
                } catch (IOException e) {
                    logger.error(e);
                    close();
                }
                state = ConnectionState.Listening;
                int receivedBytes = 0;
                byte[] headerBytes = new byte[4];
                ByteBuffer byteBuffer = null;
                int messageLength = 0;
                byte b;
                while (!ConnectionState.Closed.equals(state)) {
                    try {
                        checkInitDataInputStream();
                        b = dataInputStream.readByte();
                        //Si recibimos datos del servicio, indicamos que estamos en el estado de Listening.
                        state = ConnectionState.Listening;
                    } catch (IOException e) {
                        close();
                        continue;
                    }
                    receivedBytes++;
                    if (receivedBytes < 5) {
                        if (receivedBytes - 1 == 0 && b != 3) {
                            close();
                            continue;
                        }
                        headerBytes[receivedBytes - 1] = b;
                    } else {
                        if (byteBuffer == null) {
                            messageLength = new TPKTHeader(headerBytes, 0).getLength();
                            byteBuffer = ByteBuffer.allocate(messageLength - 4);
                        }
                        byteBuffer.put(b);
                        if (receivedBytes == messageLength) {
                            //recibimos el mensaje entero
                            try {
                                byte[] messageBytes = byteBuffer.array();
                                byteBuffer.clear();
                                byteBuffer = null;
                                receivedBytes = 0;

                                receivedMessage = (new String(messageBytes, "utf-8")).trim();
                            } catch (IOException e) {
                                logger.error(e);
                                continue;
                            }
                            try {
                                onMitAcdMessageReceived(receivedMessage);
                            } catch (Exception e) {
                                logger.error(e);
                            }
                        }
                    }
                }
            }
        }

        private synchronized void send (){
            //TODO
        }

        private void checkInitDataOutPutStream() throws IOException {
            if (dataOutputStream == null && socket != null) {
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
            }
        }

        private void checkInitDataInputStream() throws IOException {
            if (dataInputStream == null && socket != null) {
                dataInputStream = new DataInputStream(socket.getInputStream());
            }

        }

        private void closeSocket() {
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    logger.error(e);
                }
                socket = null;
            }
        }

        private void disposeDataOutPutStream() {
            disposeClosable(dataOutputStream);
            dataOutputStream = null;
        }

        private void disposeClosable(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }

        private void disposeDataInputStream() {
            disposeClosable(dataInputStream);
            dataInputStream = null;
        }

        private void disposeConnection() {
            disposeClosable(dataInputStream);
            disposeClosable(dataOutputStream);
            closeSocket();
        }

        private void close(){
            disposeConnection();
            disposeDataInputStream();
            disposeDataOutPutStream();
            if (state != ConnectionState.Closed) {
                state = ConnectionState.Closed;
            }
        }
    }
}
