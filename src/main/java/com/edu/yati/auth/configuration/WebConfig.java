/*package com.edu.yati.auth.configuration;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	Logger logger = LoggerFactory.getLogger(WebConfig.class);
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	logger.debug("cors registry " + registry);
    	registry.addMapping("/**") //
        .allowedOrigins("*") //
        .allowedMethods("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH") //
        .allowedHeaders("X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers") //
        //.exposedHeaders("X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers") //
        .allowCredentials(true)
        .maxAge(TimeUnit.DAYS.toSeconds(1));
    }
}*/