package com.edu.yati.dao.problem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.edu.yati.model.Bucket;
import com.edu.yati.model.IntegerProblem;
import com.edu.yati.model.MatrixProblem;
import com.edu.yati.model.Problem;
import com.edu.yati.model.ProblemType;
import com.edu.yati.model.SingleMoreProblem;

@Repository
public class ProblemDaoImpl implements ProblemDao {

	public static final String EDUCATION_PROBLEM = "EDUCATION_PROBLEM";
	
	@Autowired 
    private MongoTemplate mongoTemplate_;
	
	@Override
	public List<Problem> listAllProblems(String challengeId) {
		final Query q = new Query().addCriteria(Criteria.where("challengeId").is(challengeId));
        final List<Problem> result = mongoTemplate_.find(q, Problem.class,
        		EDUCATION_PROBLEM);
        return result;
	}

	@Override
	public Problem getProblemDetails(String problemId) {
		final Query q = new Query().addCriteria(Criteria.where("_id").is(problemId));
        return mongoTemplate_.findOne(q, Problem.class, EDUCATION_PROBLEM);
	}

	@Override
	public void createProblem(Problem problemModel) {
		mongoTemplate_.insert(problemModel, EDUCATION_PROBLEM);
	}

	@Override
	public void updateProblem(Problem problemModel) {
		final Query q = new Query().addCriteria(Criteria.where("_id").is(problemModel.getId()));
        final Update update = new Update();
        update.set("questionType", problemModel.getQuestionType());
        update.set("score", problemModel.getScore());
        update.set("penalty", problemModel.getPenalty());
        update.set("challengeId", problemModel.getChallengeId());
        update.set("matrixProblem", problemModel.getMatrixProblem());
        update.set("integerProblem", problemModel.getIntegerProblem());
        update.set("singleMoreProblem", problemModel.getSingleMoreProblem());
        update.set("buckets", problemModel.getBuckets());
        
        //update.set("startTime", problemModel.getName());
        mongoTemplate_.updateFirst(q, update, Problem.class, EDUCATION_PROBLEM);
	}

	@Override
	public void deleteProblem(String problemId) {
		final Query q = new Query().addCriteria(Criteria.where("_id").is(problemId));
        mongoTemplate_.remove(q, Problem.class, EDUCATION_PROBLEM);
	}

	@Override
	public long getProblemCount(String challengeId) {
		final Query q = new Query().addCriteria(Criteria.where("challengeId").is(challengeId));
		return mongoTemplate_.count(q, Problem.class, EDUCATION_PROBLEM);
	}

}
