package net.mitrol.focus.supervisor.mitct.mitacd.event;

public class MitAcdEvent {

    public static final String EVENT_RESPONSE = "{\"register_event_response\":{\"charset\": \"UTF-8\"}}\r\n";

    private String type;
    private String payload;

    public MitAcdEvent() {
    }

    public MitAcdEvent(String type, String payload) {
        this.type = type;
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
