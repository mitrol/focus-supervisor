package net.mitrol.focus.supervisor.mitacd.connector.tcp;

public class SockMessage {

    public static final String EVENT_RESPONSE = "{\"register_event_response\":{\"charset\": \"UTF-8\"}}\r\n";

    private Object type;
    private Object payload;
    private String receivedMessage;

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getReceivedMessage() {
        return receivedMessage;
    }

    public void setReceivedMessage(String receivedMessage) {
        this.receivedMessage = receivedMessage;
    }
}
