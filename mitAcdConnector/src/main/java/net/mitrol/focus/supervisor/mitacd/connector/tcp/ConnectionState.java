package net.mitrol.focus.supervisor.mitacd.connector.tcp;

public enum ConnectionState {
    Connected,
    Disconnected,
    Reconnecting,
    Closed,
    Listening;

    private ConnectionState (){
    }
}
