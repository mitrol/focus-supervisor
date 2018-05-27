package net.mitrol.focus.supervisor.common;

import net.mitrol.focus.supervisor.common.configuration.CommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ladassus
 */
@SpringBootApplication
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonConfig.class, args);
    }
}
