package com.edu.test.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySources(value = { @PropertySource("classpath:/application.properties") })
public class TestConfig {

    // **** Bean definitions for application.properties and messages.properties
    // files.

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /*@Bean
    public EducationLogger educationLogger(final LogCatalog logCatalog) {
        return new EducationLoggerImpl(logCatalog, null);
    }

    @Bean
    public LogCatalog logCatalog() {
        return new LogCatalog();
    }*/

}
