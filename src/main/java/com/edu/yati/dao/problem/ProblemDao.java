package com.edu.yati.dao.problem;

import java.util.List;

import com.edu.yati.model.Problem;

public interface ProblemDao {

	List<Problem> listAllProblems(String challengeId);

	Problem getProblemDetails(String problemId);

	void createProblem(Problem problemModel);

	void updateProblem(Problem problemModel);

	void deleteProblem(String problemId);

	long getProblemCount(String challengeId);

}
