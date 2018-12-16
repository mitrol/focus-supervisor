package net.mitrol.focus.supervisor.mitacd.connector.configuration;

import net.mitrol.acd.client.entities.MitAcdConnectionInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MitAcdConnectorContext {
    @Value("${mitacd.conn.host}")
    private String mitadcdHost;

    @Value("${mitacd.conn.port}")
    private String mitadcdPort;

    @Bean
    public MitAcdConnectionInfo connectionInfo() {
        return new MitAcdConnectionInfo(this.mitadcdHost, Integer.valueOf(this.mitadcdPort));
    }
}
