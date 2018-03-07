package com.edu.test.integration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration
@PropertySource("classpath:application.properties")
public class TestFrameworkConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    Environment env;

    @Value("${mongo.databaseurl}")
    private String mongoDbUrl;

    @Value("${mongo.username}")
    private String mongoUsername;

    @Value("${mongo.password}")
    private String mongoPassword;

    @Value("${mongo.dbname}")
    private String mongoDbname;

    @Bean
    public Mongo mongo() throws Exception {
        final MongoClient client = new MongoClient();
        client.setWriteConcern(WriteConcern.SAFE);
        return client;
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        new UserCredentials(mongoUsername, mongoPassword);
        return new SimpleMongoDbFactory(mongo(), mongoDbname);
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate() throws Exception {
        final MongoTemplate template = new MongoTemplate(mongoDbFactory());
        return template;
    }

}
