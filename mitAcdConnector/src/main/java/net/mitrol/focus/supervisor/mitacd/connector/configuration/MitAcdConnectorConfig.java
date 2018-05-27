package net.mitrol.focus.supervisor.mitacd.connector.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import net.mitrol.acd.client.entities.MitAcdConnectionInfo;

/**
 * @author ladassus
 *
 * */
@Configuration
@ComponentScan("net.mitrol.focus.supervisor.mitacd.connector.service")
public class MitAcdConnectorConfig {

	@Value("${mitacd.conn.host}")
	private String mitadcdHost;
	
	@Value("${mitacd.conn.port}")
	private String mitadcdPort;

	@Bean
	public MitAcdConnectionInfo connectionInfo() {
		return new MitAcdConnectionInfo(this.mitadcdHost, Integer.valueOf(this.mitadcdPort));
	}
}
