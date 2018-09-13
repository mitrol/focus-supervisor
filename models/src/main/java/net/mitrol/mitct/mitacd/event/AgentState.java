package net.mitrol.mitct.mitacd.event;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Properties;

public enum  AgentState {
    Unstaffed(0),
    Avail(1),
    Preview(2),
    Dial(3),
    Ring(4),
    Connect(5),
    Hold(6),
    AfterCallWork(7),
    NotReady(8),
    Break0(9),
    Break1(10),
    Break2(11),
    Break3(12),
    Break4(13),
    Break5(14),
    Break6(15),
    Break7(16),
    Break8(17),
    Break9(18),
    BreakGenerico(-2),
    Empty(-1);

    private static Properties properties;
    private final int code;
    private String description;
    private Logger logger = Logger.getLogger(net.mitrol.ct.api.enums.AgentState.class);

    private AgentState(int code) {
        this.code = code;
        //this.init();
    }

    public static AgentState getFromCode(int code) {
        return (AgentState)Arrays.asList(values()).stream().filter((x) -> {
            return x.code == code;
        }).findFirst().orElse(null);
    }

    public static AgentState getFromName(String name) {
        AgentState[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            AgentState estado = var1[var3];
            if (estado.toString().equalsIgnoreCase(name)) {
                return estado;
            }
        }

        return null;
    }

    public static boolean isEmpty(net.mitrol.ct.api.enums.AgentState ea) {
        return ea.isEmpty();
    }

    /*private void init() {
        if (properties == null) {
            properties = new Properties();
            InputStream inputStream = this.getClass().getResourceAsStream("/messages.properties");

            try {
                properties.load(inputStream);
                inputStream.close();
            } catch (IOException var3) {
                this.logger.error(var3);
            }
        }

        if (this.description == null) {
            this.description = properties.getProperty(String.format("estadoAgente.%s", this.name()));
        }

    }*/

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode() {
        return this.code;
    }

    public boolean isEmpty() {
        return this.equals(Empty);
    }

    public boolean isInGroup() {
        return this == Avail || this == NotReady || this.isProductive() || this.isBreak();
    }

    public boolean isProductive() {
        return this == AfterCallWork || this == Connect || this == Dial || this == Hold || this == Preview || this == Ring;
    }

    public boolean isBreak() {
        return this == Break0 || this == Break1 || this == Break2 || this == Break3 || this == Break4 || this == Break5 || this == Break6 || this == Break7 || this == Break8 || this == Break9 || this == BreakGenerico;
    }

    public boolean isRequestableByAgent() {
        return this == Avail || this == NotReady || this.isBreak();
    }
}
