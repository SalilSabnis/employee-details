package com.salil.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import net.snowflake.client.jdbc.SnowflakeBasicDataSource;


@Configuration
public class SnowflakeConfig {

	@Value("${snowflake.datasource.servername}")
	private String serverName;


	@Value("${snowflake.datasource.username}")
	private String userName;

	@Value("${snowflake.datasource.password}")
	private String password;


	@Value("${snowflake.datasource.databasename}")
	private String databaseName;



	@Bean(name = "dataSourceSnowflake")
	public DataSource snowFlakeDataSource() {
		SnowflakeBasicDataSource dataSource = new SnowflakeBasicDataSource();
		dataSource.setServerName(serverName);
		dataSource.setUser(userName);
		dataSource.setPassword(password);
		dataSource.setDatabaseName(databaseName);
		return dataSource;
	}


	@Bean(name = "namedJdbcTemplateSnowflake")
	@Qualifier("snowflakeJdbctemplate")
	public NamedParameterJdbcTemplate namedJdbcTemplateSnowflake(@Qualifier("dataSourceSnowflake") DataSource ds) {
		return new NamedParameterJdbcTemplate(ds);
	}
}
