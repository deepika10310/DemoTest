package com.edu.yati.dao.challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.model.Challenge;

@Repository
public class ChallengeDaoImpl implements ChallengeDao {
	
	public static final String EDUCATION_CHALLENGE = "EDUCATION_CHALLENGE";
	
	@Autowired 
    private MongoTemplate mongoTemplate_;
	
	@Override
	public List<Challenge> listAllChallenges() {
		final Query q = new Query();
        final List<Challenge> result = mongoTemplate_.find(q, Challenge.class,
        		EDUCATION_CHALLENGE);
        return result;
	}

	@Override
	public Challenge getChallengeDetails(String id) {
		final Query q = new Query().addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate_.findOne(q, Challenge.class, EDUCATION_CHALLENGE);
	}

	@Override
	public boolean isChallengeExist(Challenge challengeModel) {
		final Query q = new Query().addCriteria(Criteria.where("_id").is(challengeModel.getId()));
		return mongoTemplate_.findOne(q, Challenge.class, EDUCATION_CHALLENGE) != null;
	}

	@Override
	public Challenge createChallenge(Challenge challengeModel) {
		mongoTemplate_.insert(challengeModel, EDUCATION_CHALLENGE);
		return challengeModel;
	}

	@Override
	public Challenge updateChallenge(String id, Challenge challengeModel) {
		final Query q = new Query().addCriteria(Criteria.where("_id").is(id));
        final Update update = Update.update("name", challengeModel.getName());
        update.set("startTime", challengeModel.getStartTime());
        update.set("endTime", challengeModel.getEndTime());
        update.set("standard", challengeModel.getStandard());
        update.set("duration", challengeModel.getDuration());
        update.set("publish", challengeModel.isPublish());
        mongoTemplate_.updateFirst(q, update, Challenge.class, EDUCATION_CHALLENGE);
        return challengeModel;
	}

	@Override
	public void deleteChallenge(String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteAllChallenges() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Challenge> listAllCurrentUserChallenges(AccountUserDetail currentUser) {
		final Query q = new Query().addCriteria(Criteria.where("standard").is(currentUser.getStandard()));
		q.addCriteria(Criteria.where("publish").is(true));
        final List<Challenge> result = mongoTemplate_.find(q, Challenge.class,
        		EDUCATION_CHALLENGE);
        return result;
	}

}
