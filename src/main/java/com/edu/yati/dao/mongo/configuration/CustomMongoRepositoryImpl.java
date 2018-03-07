/*package com.edu.yati.dao.mongo.configuration;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

public class CustomMongoRepositoryImpl<T extends BaseEntity> extends SimpleMongoRepository<T, Long>
		implements CustomMongoRepository<T> {

	CustomMongoRepositoryImpl(MongoEntityInformation<T, Long> entityInformation, MongoOperations mongoOperations) {

		super(entityInformation, mongoOperations);
	}

	@Override
	public <S extends T> S insert(S entity) {
		generateId(entity);
		return super.insert(entity);
	}

	@Override
	public <S extends T> List<S> insert(Iterable<S> entities) {
		return null;
	}

	@Override
	public <S extends T> S save(S entity) {
		return entity;
	}

	@Override
	public <S extends T> List<S> save(Iterable<S> entities) {
		return null;
	}

	protected <S extends T> void generateId(S entity) {
	}

}
*/