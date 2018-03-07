package com.edu.yati.dao.problem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.dao.challenge.ChallengeDaoImpl;
import com.edu.yati.dao.solution.SolutionDaoImpl;
import com.edu.yati.model.Challenge;
import com.edu.yati.model.ChallengeScore;
import com.edu.yati.model.Problem;
import com.edu.yati.model.Solution;
import com.edu.yati.model.StudentTimer;

@Repository
public class StudentProblemDaoImpl implements StudentProblemDao {
	Logger logger = LoggerFactory.getLogger(StudentProblemDaoImpl.class);
	public static final String EDUCATION_PROBLEM = "EDUCATION_PROBLEM";
	public static final String EDUCATION_TIMER = "EDUCATION_TIMER";
	
	@Autowired 
    private MongoTemplate mongoTemplate_;
	
	public List<Problem> convertStudentProblem(List<Problem> problemList) {
		List<Problem> studentProblemList = new ArrayList<Problem>();
		for(Problem problem : problemList) {
			switch (problem.getQuestionType()) {
				case INTEGER_TYPE:
					problem.getIntegerProblem().setSolution(null);
					break;
				case SINGLE_MORE_TYPE:
					problem.getSingleMoreProblem().setCorrectOptions(null);
					break;
				case MATRIX_MATCH_TYPE:
					problem.getMatrixProblem().setCorrectOptions(null);
					break;
			}
			studentProblemList.add(problem);
		}
		return studentProblemList;
	}
	
	@Override
	public List<Problem> listAllProblems(AccountUserDetail currentUser, String challengeId) {
		final Query q = new Query().addCriteria(Criteria.where("challengeId").is(challengeId));
        final List<Problem> result = convertStudentProblem(mongoTemplate_.find(q, Problem.class,
        		EDUCATION_PROBLEM));
        return result;
	}

	@Override
	public StudentTimer getChallengeStudentTimer(String challengeId, AccountUserDetail currentUser) {
		final Query q = new Query().addCriteria(Criteria.where("challengeId").is(challengeId));
		q.addCriteria(Criteria.where("userId").is(currentUser.getId()));
		StudentTimer studentTimer = mongoTemplate_.findOne(q, StudentTimer.class, EDUCATION_TIMER);
		if(studentTimer == null) {
			studentTimer = new StudentTimer();
			studentTimer.setChallengeId(challengeId);
			studentTimer.setServerTime(new Date().getTime());
			studentTimer.setStartTime(new Date().getTime());
			studentTimer.setUserId(currentUser.getId());
			studentTimer.setSubmitted(false);
			mongoTemplate_.insert(studentTimer, EDUCATION_TIMER);
		} else {
			studentTimer.setServerTime(new Date().getTime());
		}
		return studentTimer;
	}

	@Override
	public boolean submitTest(String challengeId, AccountUserDetail currentUser) {
		final Query q = new Query().addCriteria(Criteria.where("challengeId").is(challengeId));
		q.addCriteria(Criteria.where("userId").is(currentUser.getId()));
		StudentTimer studentTimer = mongoTemplate_.findOne(q, StudentTimer.class, EDUCATION_TIMER);
		if(studentTimer == null) {
			studentTimer = new StudentTimer();
			studentTimer.setChallengeId(challengeId);
			studentTimer.setServerTime(new Date().getTime());
			studentTimer.setStartTime(new Date().getTime());
			studentTimer.setUserId(currentUser.getId());
			studentTimer.setSubmitted(true);
			mongoTemplate_.insert(studentTimer, EDUCATION_TIMER);
		} else {
			final Update update = Update.update("submitted", true);
			mongoTemplate_.updateFirst(q, update, StudentTimer.class, EDUCATION_TIMER);
		}
		return true;
	}

	@Override
	public boolean checkFinished(String challengeId, AccountUserDetail currentUser) {
		final Query q = new Query().addCriteria(Criteria.where("challengeId").is(challengeId));
		q.addCriteria(Criteria.where("userId").is(currentUser.getId()));
		StudentTimer studentTimer = mongoTemplate_.findOne(q, StudentTimer.class, EDUCATION_TIMER);
		return studentTimer.isSubmitted();
	}

	@Override
	public List<ChallengeScore> getChallengeScore(String challengeId, AccountUserDetail currentUser) {
		final Query submitCheckQuery = new Query().addCriteria(Criteria.where("challengeId").is(challengeId));
		submitCheckQuery.addCriteria(Criteria.where("userId").is(currentUser.getId()));
		logger.debug("challengeId:::: " + challengeId + " current user " + currentUser.getId());
		StudentTimer studentTimer = mongoTemplate_.findOne(submitCheckQuery, StudentTimer.class, EDUCATION_TIMER);
		boolean showSolution = false;
		logger.debug("submitted:::: " + studentTimer.isSubmitted());
		//elapsedTime > duration
		if(studentTimer.isSubmitted()) { //if user has submitted solution
			showSolution = true;
		} else { // check end time exceeds
			final Query challengeEndTimeQuery = new Query().addCriteria(Criteria.where("_id").is(challengeId));
			Challenge challenge = mongoTemplate_.findOne(challengeEndTimeQuery, Challenge.class, ChallengeDaoImpl.EDUCATION_CHALLENGE);
			logger.debug(" challenge " + challenge + " studentTimer " + studentTimer);
			logger.debug("duration :::: " + challenge.getDuration() + " start time " 
					+ studentTimer.getStartTime() + " current time " + new Date().getTime());
			long elapsedTime = new Date().getTime() - studentTimer.getStartTime();
			if(elapsedTime > challenge.getDuration()) {
				showSolution = true;
			}
		}
		logger.debug("showSolution:::: " + showSolution);
		List<ChallengeScore> challengeScores = new ArrayList<>();
		if(showSolution) {
			final Query problemQuery = new Query().addCriteria(Criteria.where("challengeId").is(challengeId));
			List<Problem> problems = mongoTemplate_.find(problemQuery, Problem.class, ProblemDaoImpl.EDUCATION_PROBLEM);
			logger.debug("problems:::: " + problems);
			for(Problem problem: problems) {
				ChallengeScore challengeScore = new ChallengeScore();
				logger.debug("proble id:: " + problem.getId());
				final Query solutionQuery = new Query().addCriteria(Criteria.where("problemId").is(problem.getId()));
				solutionQuery.addCriteria(Criteria.where("userId").is(currentUser.getId()));
				Solution solution = mongoTemplate_.findOne(solutionQuery, Solution.class, SolutionDaoImpl.EDUCATION_SOLUTION);
				if(solution != null) {
					challengeScore.setMarksObtained(solution.getMarksObtained());
				} else {
					challengeScore.setMarksObtained(0);
				}
				challengeScore.setPenalty(problem.getPenalty());
				challengeScore.setScore(problem.getScore());
				challengeScores.add(challengeScore);
				logger.debug("score :::: " + challengeScore.getScore() + " penalty ::: " + challengeScore.getPenalty() + " marks obtained:: " + challengeScore.getMarksObtained());
			}
		}
		return challengeScores;
	}
}
