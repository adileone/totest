package it.integration.db2csv.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.integration.db2csv.MyCustomReader;
import it.integration.db2csv.MyCustomWriter;
import it.integration.db2csv.listener.LogStepListener;
import it.integration.db2csv.tasklet.BucketTasklet;
import it.integration.db2csv.vo.Employee;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
		
	@Autowired
	MyCustomReader myCustomReader;
	
	@Autowired
	MyCustomWriter myCustomWriter;
	
	@Autowired
	BucketTasklet bucketTasklet;
	
	@Autowired
	ItemProcessor<Employee, Employee> itemProcessor;
	
	@Autowired
	LogStepListener<Employee, Employee> logStepListener;
	
	@Bean
	public Job createJob() {
		return jobBuilderFactory.get("MyJob")
				.incrementer(new RunIdIncrementer())
				.start(createStep())
				.next(createStep2())
				//.flow(createStep()).end()
				.build();
	}

	@Bean
	public Step createStep() {
		return stepBuilderFactory.get("writeFile")
				.listener(logStepListener)
				.<Employee, Employee> chunk(100)
				.reader(myCustomReader)
				.processor(itemProcessor)
				.writer(myCustomWriter)
				.build();
	}

	@Bean
	public Step createStep2() {
		return stepBuilderFactory.get("moveFile").tasklet(bucketTasklet).build();
	}
		
}
