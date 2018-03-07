package com.edu.yati.dao.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration
//@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.edu.yati")
public class DaoConfig {
	Logger logger = LoggerFactory.getLogger(DaoConfig.class);
	
	/*@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}*/

	@Value("${mongo.databaseurl}")
	private String mongoDbUrl;

	@Value("${mongo.username}")
	private String mongoUsername;

	@Value("${mongo.password}")
	private String mongoPassword;

	@Value("${mongo.dbname}")
	private String mongoDbname;
	
	public DaoConfig() {
		// TODO Auto-generated constructor stub
		logger.debug("mongoDbname " + mongoDbname + " mongoDbUrl " + mongoDbUrl + " mongoUsername " + mongoUsername);
	}
	
	/*@Autowired
	@Bean
	public EducationMongoDao educationMongoDao(@Qualifier("mongoTemplate") final MongoTemplate template) {
		return new EducationMongoDaoImpl(template);
	}*/

	@Bean(name="mongo")
	public Mongo mongo() throws Exception {
		logger.debug("^^^^^^^^^^^^^ Mongo ^^^^^^^^^^^^^^^^^^^^^");
		//final MongoClient client = new MongoClient(mongoDbUrl);
		final MongoClient client = new MongoClient("127.0.0.1");
		client.setWriteConcern(WriteConcern.SAFE);
		return client;
	}

	@Bean(name="mongoDbFactory")
	public MongoDbFactory mongoDbFactory() throws Exception {
		logger.debug("^^^^^^^^^^^^^ MongoDbFactory ^^^^^^^^^^^^^^^^^^^^^ " + mongoDbname);
		//new UserCredentials(mongoUsername, mongoPassword);
		new UserCredentials("admin", "password");
		//return new SimpleMongoDbFactory(mongo(), mongoDbname);
		return new SimpleMongoDbFactory(mongo(), "education");
	}
	

	@Bean(name="mongoTemplate")
	public MongoTemplate mongoTemplate() throws Exception {
		logger.debug("^^^^^^^^^^^^^ MONGO TEMPLATE CREATON ^^^^^^^^^^^^^^^^^^^^^");
		return new MongoTemplate(mongoDbFactory());
	}
	
	// So as to translate all Persistence api specific exceptions to Unchecked
	// Spring Exceptions.
	/*@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}*/
	
	/*@Autowired
	@Bean
	public AccountDao accountDao(@Qualifier("mongoTemplate") final MongoTemplate template) {
		return new AccountDaoImpl(template);
	}*/
	/*
	@Autowired
	@Bean
	public SequenceDao sequenceDao(@Qualifier("mongoTemplate") final MongoTemplate template) {
		return new SequenceDaoImpl(template);
	}*/

	/*@Override
	protected String getDatabaseName() {
		return mongoDbname;
	}*/
}
