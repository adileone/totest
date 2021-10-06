package it.integration.db2csv.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import it.integration.db2csv.vo.Employee;

@Component
public class RecordItemProcessor implements ItemProcessor<Employee, Employee> {
	private static final Logger LOG = LoggerFactory.getLogger(RecordItemProcessor.class);
			
	public Employee process(Employee employee) throws Exception {
		LOG.debug("Processing: " + employee);
		
		final String initCapFirstName = employee.getName().substring(0, 1).toUpperCase()
				+ employee.getName().substring(1);
		Employee transformedEmployee = new Employee();
		transformedEmployee.setId(employee.getId());
		transformedEmployee.setName(initCapFirstName);
		transformedEmployee.setSalary(employee.getSalary());
		return transformedEmployee;
	}

}
