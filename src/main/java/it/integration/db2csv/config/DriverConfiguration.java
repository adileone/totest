package it.integration.db2csv.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DriverConfiguration {
	@Profile("h2")
	@Bean
	public DataSource dataSourceH2() {
		EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
		return embeddedDatabaseBuilder
				// .addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
				// .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
				.addScript("classpath:database/schema-h2.sql").setType(EmbeddedDatabaseType.H2).build();
	}
}
