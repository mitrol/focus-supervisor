package net.mitrol.focus.supervisor.connector.util;

import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ladassus
 */
@Component
public class EventMessageJob implements Job {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(EventMessageJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //TODO
        //JobKey jobKey = context.getJobDetail().getKey();
        //logger.info("SimpleJob says: " + jobKey + " executing at " + new Date());
    }
}
