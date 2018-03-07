package com.edu.yati.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EducationMongoDaoImpl implements EducationMongoDao {
	private static final String EDUCATION_COLLECTION = "EDUCATION_COLLECTION";
	private static final String EDUCATION_USER = "EDUCATION_USER";

	private final static int CURRENT_SCHEMA_VERSION = 1;
	private final MongoTemplate mongoTemplate_;

	@Autowired
	public EducationMongoDaoImpl(final MongoTemplate mongoTemplate) {
		mongoTemplate_ = mongoTemplate;
	}
	
	private void createUser() {
		mongoTemplate_.createCollection(EDUCATION_USER);
	}

	@Override
	public void createSchema() {
		if (!mongoTemplate_.collectionExists(EDUCATION_USER)) {
			createUser();
		}
	}

	@Override
	public boolean dbSchemaExist() throws Exception {
		return mongoTemplate_.collectionExists(EDUCATION_COLLECTION);
	}

	@Override
	public Integer getCurrentDatabaseVersion() {
		return CURRENT_SCHEMA_VERSION;
	}

	@Override
	public void migrateDatabase(final int sourceDbVersion, final int targetDbVersion) throws Exception {

	}
}
