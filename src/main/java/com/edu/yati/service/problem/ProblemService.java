package com.edu.yati.service.problem;

import java.util.List;

import com.edu.yati.model.Problem;

public interface ProblemService {

	List<Problem> listAllProblems(String challengeId);

	Problem getProblemDetails(String id);

	void createProblem(Problem problemModel);

	void updateProblem(Problem problemModel);

	void deleteProblem(String id);

	long getProblemCount(String challengeId);

}
