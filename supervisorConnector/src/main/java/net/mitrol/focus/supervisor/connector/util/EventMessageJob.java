package net.mitrol.focus.supervisor.connector.util;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author ladassus
 */
@Component
public class EventMessageJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //TODO
    }
}
