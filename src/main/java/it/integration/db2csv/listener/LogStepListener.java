package it.integration.db2csv.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * Spring Batch listener logging several informations during step execution.
 * 
 * <p>
 * Logs are written through SLF4J.<br>
 * This bean should be declared with <strong>step</strong> scope in order to reference it's
 * {@link StepExecution}.
 * </p>
 * 
 */
@Component
public class LogStepListener<T, S> extends StepListenerSupport<T, S> {
	
    private static final Logger LOG = LoggerFactory.getLogger(LogStepListener.class);

    private StepExecution       stepExecution;

    private Integer             commitInterval;

    @Override
    public void beforeStep(StepExecution pStepExecution) {
        this.stepExecution = pStepExecution;
    }

    @Override
    public void beforeChunk(ChunkContext chunkContext) {
        if (commitInterval != null) {
        	if(LOG.isTraceEnabled())LOG.trace("Step {} - Reading next {} items", stepExecution.getStepName(), commitInterval);
        }
    }

    @Override
    public void beforeWrite(List<? extends S> items) {
    	if(LOG.isTraceEnabled())LOG.trace("Step {} - Writing {} items", stepExecution.getStepName(), items.size());
    }

    @Override
    public void afterWrite(List<? extends S> items) {
        if ((items != null) && !items.isEmpty()) {
        	if(LOG.isTraceEnabled())LOG.trace("Step {} - {} items writed", stepExecution.getStepName(), items.size());
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution pStepExecution) {
        if (LOG.isInfoEnabled()) {
            StringBuilder msg = new StringBuilder();
            msg.append("Step ").append(pStepExecution.getStepName());
            msg.append(" - Read count: ").append(pStepExecution.getReadCount());
            msg.append(" - Write count: ").append(pStepExecution.getWriteCount());
            msg.append(" - Commit count: ").append(pStepExecution.getCommitCount());
            LOG.info(msg.toString());
        }
        return super.afterStep(pStepExecution);
    }

    /**
     * Sets the chunk commit interval
     * 
     * @param commitInterval
     *            chunk commit interval (may be <code>null</code>)
     */
    public void setCommitInterval(Integer commitInterval) {
        this.commitInterval = commitInterval;
    }

}