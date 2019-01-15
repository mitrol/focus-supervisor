package net.mitrol.focus.supervisor.mitacd.connector.tcp;

import com.google.common.reflect.TypeToken;
import net.mitrol.focus.supervisor.mitct.mitacd.event.RegisterEvent;
import net.mitrol.utils.ExecutorBuilder;
import net.mitrol.utils.json.JsonMapper;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.json.JSONException;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * @author ladassus
 */
public abstract class MitAcdConnectorServer {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(MitAcdConnectorServer.class);

    protected void init (int port) {
        ServerSocket server = null;
        Socket socket = null;
        ExecutorService executor =  ExecutorBuilder.buildNewSingleExecutorService("MitAcd");
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            logger.error(e);
            return;
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

    public abstract void onMitAcdMessageReceived(SockMessage sockMessage);

    private class MitAcdClientConn implements Runnable {
        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        private ConnectionState state = ConnectionState.Disconnected;
        private String receivedMessage;
        private Type type = new TypeToken<Map<String, Object>>(){}.getType();

        public MitAcdClientConn (Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            if (this.socket.isConnected()) {
                this.state = ConnectionState.Connected;
                this.disposeDataInputStream();
                this.disposeDataOutPutStream();
                try {
                    this.checkInitDataInputStream();
                    this.checkInitDataOutPutStream();
                } catch (IOException e) {
                    logger.error(e);
                    this.close();
                }
                this.state = ConnectionState.Listening;
                int receivedBytes = 0;
                byte[] headerBytes = new byte[4];
                ByteBuffer byteBuffer = null;
                int messageLength = 0;
                byte b;
                while (!ConnectionState.Closed.equals(this.state)) {
                    try {
                        this.checkInitDataInputStream();
                        b = this.dataInputStream.readByte();
                        //if we receive data from the service we are indicating state Listening.
                        this.state = ConnectionState.Listening;
                        logger.debug(String.format("MitAcd received byte: %s", b));
                    } catch (IOException e) {
                        this.close();
                        continue;
                    }
                    receivedBytes++;
                    logger.debug(String.format("MitAcd received bytes count: %s", receivedBytes));
                    if (receivedBytes < 5) {
                        if (receivedBytes - 1 == 0 && b != 3) {
                            this.close();
                            continue;
                        }
                        headerBytes[receivedBytes - 1] = b;
                    } else {
                        if (byteBuffer == null) {
                            messageLength = new TPKTHeader(headerBytes, 0).getLength();
                            byteBuffer = ByteBuffer.allocate(messageLength - 4);
                        }
                        logger.debug(String.format("MitAcd received message byte %s/%s", receivedBytes, messageLength));
                        byteBuffer.put(b);
                        if (receivedBytes == messageLength) {
                            //we receive the all message.
                            try {
                                byte[] messageBytes = byteBuffer.array();
                                logger.debug(String.format("Message received %s", new String(messageBytes, "UTF-8")));
                                byteBuffer.clear();
                                byteBuffer = null;
                                receivedBytes = 0;

                                this.receivedMessage = (new String(messageBytes, "utf-8")).trim();
                            } catch (IOException e) {
                                logger.error(e, String.format("Error message parser : %s", new String(byteBuffer.array())));
                                continue;
                            }
                            logger.debug(String.format("Processing MitAcdMessage %s started", this.receivedMessage));
                            try {
                                SockMessage sockMessage = this.buildSockMessage(this.receivedMessage);
                                if (sockMessage.getType().equals(RegisterEvent.TYPE)) {
                                    this.send(SockMessage.EVENT_RESPONSE);
                                } else {
                                    onMitAcdMessageReceived(sockMessage);
                                }
                            } catch (JSONException e1) {
                                logger.error(String.format("Error JSON parser: %s" , this.receivedMessage));
                            }
                            logger.debug(String.format("Processing MitAcdMessage %s finished", this.receivedMessage));
                        }
                    }
                }
                logger.info("MitAcd socket listening finished");
            }
        }

        private synchronized void send (String string){
            logger.debug(String.format("MitAcd socket sending message response %s ", string));
            if (this.socket != null && this.socket.isConnected()) {
                try {
                    Charset encoding = Charset.forName("UTF-8");
                    byte[] data = string.getBytes(encoding);
                    int finalDataLength = 4 + data.length;
                    byte[] tpkHeaderBytes = (new TPKTHeader(finalDataLength)).toNetworkByteArray();
                    this.checkInitDataOutPutStream();
                    this.checkInitDataInputStream();
                    this.dataOutputStream.write(tpkHeaderBytes, 0, tpkHeaderBytes.length);
                    this.dataOutputStream.write(data, 0, data.length);
                    this.dataOutputStream.flush();
                } catch (Exception e){
                    logger.error(e, String.format("Error MitAcd socket sending message response %s ", string));
                }
            }
        }

        private void checkInitDataOutPutStream() throws IOException {
            if (this.dataOutputStream == null && this.socket != null) {
                this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
            }
        }

        private void checkInitDataInputStream() throws IOException {
            if (this.dataInputStream == null && this.socket != null) {
                this.dataInputStream = new DataInputStream(this.socket.getInputStream());
            }

        }

        private void closeSocket() {
            if (this.socket != null && !this.socket.isClosed()) {
                try {
                    this.socket.close();
                } catch (IOException e) {
                    logger.error(e);
                }
                this.socket = null;
            }
        }

        private void disposeDataOutPutStream() {
            disposeClosable(this.dataOutputStream);
            this.dataOutputStream = null;
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
            disposeClosable(this.dataInputStream);
            this.dataInputStream = null;
        }

        private void disposeConnection() {
            disposeClosable(this.dataInputStream);
            disposeClosable(this.dataOutputStream);
            closeSocket();
        }

        private void close(){
            disposeConnection();
            disposeDataInputStream();
            disposeDataOutPutStream();
            if (this.state != ConnectionState.Closed) {
                this.state = ConnectionState.Closed;
            }
        }

        private SockMessage buildSockMessage (String receivedMessage) throws JSONException {
            Map<String, Object> map = JsonMapper.getInstance().getObjectFromString(receivedMessage, this.type);
            Map.Entry entry = this.getEntry(map, 0);
            SockMessage sockMessage = new SockMessage();
            sockMessage.setReceivedMessage(receivedMessage);
            sockMessage.setType(entry.getKey());
            sockMessage.setPayload(entry.getValue());
            return sockMessage;
        }

        @SuppressWarnings("unchecked")
        private Map.Entry<String, Object> getEntry(Map map, int i){
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            int j = 0;
            for(Map.Entry<String, Object>entry : entries) {
                if (j++ == i) {
                    return entry;
                }
            }
            return null;
        }
    }
}
