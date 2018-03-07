package com.edu.yati.auth.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.edu.yati.dao.configuration.DaoConfig;
import com.edu.yati.s3.configuration.S3Configuration;

@Configuration
@Import({ DaoConfig.class, S3Configuration.class })
@PropertySources({ @PropertySource("classpath:application.properties")})
@EnableWebMvc
@ComponentScan(basePackages = "com.edu.yati")
public class EducationConfiguration {
	@Value("${mongo.databaseurl}")
	private String mongoDbUrl;

	@Value("${mongo.username}")
	private String mongoUsername;

	@Value("${mongo.password}")
	private String mongoPassword;

	@Value("${mongo.dbname}")
	private String mongoDbname;
	
	Logger logger = LoggerFactory.getLogger(EducationConfiguration.class);
	public EducationConfiguration() {
		logger.debug("mongoDbname " + mongoDbname + " mongoDbUrl " + mongoDbUrl + " mongoUsername " + mongoUsername);
	}
}