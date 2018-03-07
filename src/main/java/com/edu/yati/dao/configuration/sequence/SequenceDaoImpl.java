/*package com.edu.yati.dao.configuration.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class SequenceDaoImpl implements SequenceDao {

	//@Autowired
	//private MongoOperations mongoOperation;
	private final MongoTemplate mongoTemplate_;
	
	@Autowired
    public SequenceDaoImpl(final MongoTemplate mongoTemplate) {
        mongoTemplate_ = mongoTemplate;
    }

	@Override
	public long getNextSequenceId(String key) throws SequenceException {

	  //get sequence id
	  Query query = new Query(Criteria.where("_id").is(key));

	  //increase sequence id by 1
	  Update update = new Update();
	  update.inc("seq", 1);

	  //return new increased id
	  FindAndModifyOptions options = new FindAndModifyOptions();
	  options.returnNew(true);

	  //this is the magic happened.
	  SequenceId seqId =
			  mongoTemplate_.findAndModify(query, update, options, SequenceId.class);
	  
	  //if no id, throws SequenceException
          //optional, just a way to tell user when the sequence id is failed to generate.
	  if (seqId == null) {
		throw new SequenceException("Unable to get sequence id for key : " + key);
	  }
	  return seqId.getSeq();
	}

}*/