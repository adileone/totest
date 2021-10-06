package it.integration.db2csv.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.integration.db2csv.storage.GCSStorage;

@Component
public class BucketTasklet implements Tasklet {

	@Autowired
	GCSStorage storage;
	@Value("${file.output}")
	private String outpufile;	
	@Value("${file.blob-name}")
	private String blobName;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		storage.send(outpufile, blobName);
		return RepeatStatus.FINISHED;
	}

}
