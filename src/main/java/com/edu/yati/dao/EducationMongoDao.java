package com.edu.yati.dao;

public interface EducationMongoDao {
	void createSchema();

    boolean dbSchemaExist() throws Exception;

    Integer getCurrentDatabaseVersion();

    void migrateDatabase(int sourceDbVersion, int targetDbVersion)
            throws Exception;
}
