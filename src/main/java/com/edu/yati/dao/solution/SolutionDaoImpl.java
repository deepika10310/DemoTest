package com.edu.yati.dao.solution;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.dao.problem.StudentProblemDaoImpl;
import com.edu.yati.model.MatrixProblemColumn;
import com.edu.yati.model.MatrixSolution;
import com.edu.yati.model.Problem;
import com.edu.yati.model.Solution;
import com.edu.yati.model.StudentTimer;

@Repository
public class SolutionDaoImpl implements SolutionDao {
	Logger logger = LoggerFactory.getLogger(SolutionDaoImpl.class);
	public static final String EDUCATION_SOLUTION = "EDUCATION_SOLUTION";

	@Autowired
	private MongoTemplate mongoTemplate_;
	private Problem problem;

	private double calculateMarks(Problem problem, Solution solutionModel) {
		logger.debug("calculateMarks problem " + problem + " solutionModel " + solutionModel);
		double marks = 0;
		logger.debug("problem " + problem);
		logger.debug("question type " + problem.getQuestionType());
		switch (problem.getQuestionType()) {
		
		case INTEGER_TYPE:
			String[] correctSolutions = problem.getIntegerProblem().getSolution();
			String[] userSolutions = solutionModel.getIntegerSolution().split(",");
			boolean isCorrect = true;
			if (userSolutions.length == correctSolutions.length) {
				Arrays.sort(userSolutions);
				Arrays.sort(correctSolutions);
				for (int i = 0; i < userSolutions.length; i++) {
					if (!userSolutions[i].equals(correctSolutions[i])) {
						isCorrect = false;
						break;
					}
				}
			} else {
				isCorrect = false;
			}
			if (isCorrect) {
				marks += problem.getScore();
			} else {
				marks -= problem.getPenalty();
			}
			break;
		case SINGLE_MORE_TYPE:
			Set<String> correctSingleSolutions = problem.getSingleMoreProblem().getCorrectOptions();
			Set<String> userSingleSolution = solutionModel.getSingleMoreSolution();
			boolean isSingleCorrect = true;
			if (!correctSingleSolutions.containsAll(userSingleSolution)) {
				isSingleCorrect = false;
			}
			if (!userSingleSolution.containsAll(correctSingleSolutions)) {
				isSingleCorrect = false;
			}
			if (isSingleCorrect) {
				marks += problem.getScore();
			} else {
				marks -= problem.getPenalty();
			}
			break;
		case MATRIX_MATCH_TYPE:
			logger.debug("problem " + problem);
			logger.debug("problem.getMatrixProblem() " + problem.getMatrixProblem());
			Set<String> correctMatrixSolutions = problem.getMatrixProblem().getCorrectOptions();
			logger.debug("correctMatrixSolutions " + correctMatrixSolutions);
			logger.debug("solutionModel " + solutionModel);
			Set<String> userMatrixSolutions = solutionModel.getMatrixSolution();
			logger.debug("userMatrixSolutions " + userMatrixSolutions);
			logger.debug("score " + problem.getScore());
			logger.debug("penalty " + problem.getPenalty());
			double perScore = (double)problem.getScore() / 4;
			double perPenalty = (double)problem.getPenalty() / 4;
			logger.debug("perScore " + perScore + " perPenalty " + perPenalty);
			char[] options = {'A', 'B', 'C', 'D'};
			marks = calculateMatrixMarks(userMatrixSolutions, correctMatrixSolutions, options, perScore, perPenalty);
			break;
		default:
			break;
		}
		return marks;
	}
	
	private double calculateMatrixMarks(Set<String> userSolution, Set<String> correctSolution, char[] options, double perScore, double perPenalty) {
		double marks = 0;
		Set<String> userOptionSet = new HashSet<>();
		Set<String> correctOptionSet = new HashSet<>();
		logger.debug("perScore " + perScore + " perPenalty " + perPenalty);
		for(char option : options) {
			logger.debug("option " + option);
			boolean isCorrect = true;
			userOptionSet.clear();
			for(String userSol : userSolution) {
				logger.debug("userSol.charAt(0) " + userSol.charAt(0) + " is equal " + (userSol.charAt(0) == option));
				if(userSol.charAt(0) == option) {
					userOptionSet.add(userSol);
				}
			}
			correctOptionSet.clear();
			for(String correctSol : correctSolution) {
				logger.debug("correctSol.charAt(0) " + correctSol.charAt(0) + " is equal " + (correctSol.charAt(0) == option));
				if(correctSol.charAt(0) == option) {
					correctOptionSet.add(correctSol);
				}
			}
			logger.debug("userOptionSet " + userOptionSet);
			logger.debug("correctOptionSet " + correctOptionSet);
			if (!userOptionSet.containsAll(correctOptionSet)) {
				isCorrect = false;
			}
			if (!correctOptionSet.containsAll(userOptionSet)) {
				isCorrect = false;
			}
			
			logger.debug("useroption size " + userOptionSet.size());
			if(userOptionSet.size() > 0) {
				if (isCorrect) {
					marks += perScore;
				} else {
					marks -= perPenalty;
				}
			}
			
			logger.debug("marks " + marks + " option " + option);
		}
		return marks;
	}

	private Solution createSolutionObject(AccountUserDetail currentUser, Solution solutionModel) {
		logger.debug("createSolutionObject currentUser " + currentUser + " solutionModel " + solutionModel);
		logger.debug("getProblemId " + solutionModel.getProblemId());
		// Query query = new
		// Query().addCriteria(Criteria.where("_id").is(solutionModel.getProblemId()));
		// Problem problem = mongoTemplate_.findOne(query, Problem.class,
		// StudentProblemDaoImpl.EDUCATION_PROBLEM);
		Problem problem = getProblemById(solutionModel.getProblemId());
		this.problem = problem;
		logger.debug("problem " + problem);
		solutionModel.setMarksObtained(calculateMarks(problem, solutionModel));
		solutionModel.setUserId(currentUser.getId());
		solutionModel.setUsername(currentUser.getUsername());
		return solutionModel;
	}

	Problem getProblemById(String problemId) {
		logger.debug("createSolutionObject problemId " + problemId);
		Query query = new Query().addCriteria(Criteria.where("_id").is(problemId));
		Problem problem = mongoTemplate_.findOne(query, Problem.class, StudentProblemDaoImpl.EDUCATION_PROBLEM);
		return problem;
	}

	@Override
	public void createSolution(AccountUserDetail currentUser, Solution solutionModel) {
		logger.debug("createSolution currentUser " + currentUser + " solutionModel " + solutionModel);
		solutionModel = createSolutionObject(currentUser, solutionModel);
		Query query = new Query().addCriteria(Criteria.where("userId").is(solutionModel.getUserId()));
		query.addCriteria(Criteria.where("problemId").is(solutionModel.getProblemId()));

		logger.debug("userid " + solutionModel.getUserId() + " problem id " + solutionModel.getProblemId());
		logger.debug("marks " + solutionModel.getMarksObtained());

		final Update update = Update.update("marksObtained", solutionModel.getMarksObtained());

		switch (this.problem.getQuestionType()) {
		case INTEGER_TYPE:
			update.set("integerSolution", solutionModel.getIntegerSolution());
			// logger.debug("integer solution " +
			// solutionModel.getIntegerSolution());
			break;
		case SINGLE_MORE_TYPE:
			update.set("singleMoreSolution", solutionModel.getSingleMoreSolution());
			// logger.debug("singleMoreSolution " +
			// solutionModel.getSingleMoreSolution());
			break;
		case MATRIX_MATCH_TYPE:
			update.set("matrixSolution", solutionModel.getMatrixSolution());
			break;
		}

		mongoTemplate_.upsert(query, update, EDUCATION_SOLUTION);
		// mongoTemplate_.insert(createSolutionObject(currentUser,
		// solutionModel), EDUCATION_SOLUTION);
	}

	@Override
	public Solution getStudentSolution(String problemId, String userId) {
		Query q = new Query(Criteria.where("problemId").is(problemId));
		q.addCriteria(Criteria.where("userId").is(userId));
		return mongoTemplate_.findOne(q, Solution.class, EDUCATION_SOLUTION);
	}
	
	private boolean submitTest(String challengeId, AccountUserDetail currentUser) {
		final Query q = new Query().addCriteria(Criteria.where("challengeId").is(challengeId));
		q.addCriteria(Criteria.where("userId").is(currentUser.getId()));
		StudentTimer studentTimer = mongoTemplate_.findOne(q, StudentTimer.class, StudentProblemDaoImpl.EDUCATION_TIMER);
		if(studentTimer == null) {
			studentTimer = new StudentTimer();
			studentTimer.setChallengeId(challengeId);
			studentTimer.setServerTime(new Date().getTime());
			studentTimer.setStartTime(new Date().getTime());
			studentTimer.setUserId(currentUser.getId());
			studentTimer.setSubmitted(true);
			mongoTemplate_.insert(studentTimer, StudentProblemDaoImpl.EDUCATION_TIMER);
		} else {
			final Update update = Update.update("submitted", true);
			mongoTemplate_.updateFirst(q, update, StudentTimer.class, StudentProblemDaoImpl.EDUCATION_TIMER);
		}
		return true;
	}

	@Override
	public boolean submitChallenge(String challengeId, AccountUserDetail currentUser,
			Solution[] completeSolution) {
		for(Solution solution : completeSolution) {
			solution.setChallengeId(challengeId);
			createSolution(currentUser, solution);
		}
		submitTest(challengeId, currentUser);
		return false;
	}
}
