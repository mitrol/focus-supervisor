package net.mitrol.focus.supervisor.mitacd.connector.tcp;

public class TPKTHeader {
    public static final int HEADER_LENGTH = 4;

    private byte version = 3;
    private byte reserved = 0;
    private int length;

    public TPKTHeader(int length) {
        this.length = length;
    }

    private TPKTHeader(byte version, byte reserved, short length) {
        this.version = version;
        this.reserved = reserved;
        this.length = length;
    }

    public TPKTHeader(byte[] array, int offset) {
        short length = (short) (((array[offset + 2] << 8) & 0xFF00) | (array[offset + 3] & 0xFF));
        this.version = array[offset + 0];
        this.reserved = array[offset + 1];
        this.length = length;
    }

    public byte[] toNetworkByteArray() {
        return new byte[]{version, reserved, (byte) (length >> 8), (byte) (length & 0xff)};
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte getReserved() {
        return reserved;
    }

    public void setReserved(byte reserved) {
        this.reserved = reserved;
    }

    public int getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }
}
