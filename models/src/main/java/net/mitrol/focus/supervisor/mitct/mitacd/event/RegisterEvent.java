package net.mitrol.focus.supervisor.mitct.mitacd.event;

public class RegisterEvent {

    public static final String TYPE = "register_event";

    private String charset;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
