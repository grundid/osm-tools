package org.osmsurround;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DbConfiguration {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Bean
	public DataSource getDataSource() throws IOException {

		Properties properties = new Properties();
		String propertiesFile = System.getProperty("db.properties");
		if (propertiesFile != null) {
			log.info("Using " + propertiesFile);
			properties.load(new FileInputStream(propertiesFile));
		}
		else {
			log.info("Using class path db.properties");
			properties.load(ClassLoader.getSystemResourceAsStream("db.properties"));
		}

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(properties.getProperty("db.driver"));
		basicDataSource.setUrl(properties.getProperty("db.url"));
		basicDataSource.setUsername(properties.getProperty("db.user"));
		basicDataSource.setPassword(properties.getProperty("db.password"));
		return basicDataSource;
	}

	@Bean
	@Autowired
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
