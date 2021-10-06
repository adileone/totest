package it.integration.db2csv;

import javax.annotation.PostConstruct;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import it.integration.db2csv.vo.Employee;

@Component
public class MyCustomWriter extends FlatFileItemWriter<Employee> {

	@Value("${file.output}")
	private String outpufile;
	
	@PostConstruct
	private void setup() {
		setResource(new FileSystemResource(outpufile));
		setLineAggregator(getDelimitedLineAggregator());
	}
	
	public DelimitedLineAggregator<Employee> getDelimitedLineAggregator() {
		BeanWrapperFieldExtractor<Employee> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<Employee>();
		beanWrapperFieldExtractor.setNames(new String[] {"id", "name", "salary"});

		DelimitedLineAggregator<Employee> delimitedLineAggregator = new DelimitedLineAggregator<Employee>();
		delimitedLineAggregator.setDelimiter(",");
		delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
		return delimitedLineAggregator;
		
	}
}
