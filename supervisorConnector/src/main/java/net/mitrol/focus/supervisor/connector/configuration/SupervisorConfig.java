package net.mitrol.focus.supervisor.connector.configuration;

import net.mitrol.focus.supervisor.connector.service.SupervisorEventService;
import net.mitrol.focus.supervisor.core.service.config.ElasticSearchConfig;
import net.mitrol.focus.supervisor.core.service.config.ServiceConfig;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author ladassus
 */
@Configuration
@Import({ServiceConfig.class, ElasticSearchConfig.class})
@ComponentScan("net.mitrol.focus.supervisor.connector.service," +
               "net.mitrol.focus.supervisor.core.service")
@PropertySources({@PropertySource("classpath:application.properties")})
public class SupervisorConfig {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(SupervisorEventService.class);

    @Bean
    public Scheduler scheduler() throws IOException, SchedulerException {
        //
        StdSchedulerFactory factory = new StdSchedulerFactory();
        factory.initialize(new ClassPathResource("quartz.properties").getInputStream());
        //
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        return scheduler;
    }
}
