package it.integration.db2csv.listener;

import javax.batch.runtime.JobExecution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger LOG = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	public void beforeJob(JobExecution jobExecution) {
		if(LOG.isTraceEnabled())LOG.trace("Called beforeJob().");
	}

	public void afterJob(JobExecution jobExecution) {
		if(LOG.isTraceEnabled())LOG.trace("Called afterJob().");
	}
}