package com.edu.yati.service.solution;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.model.Solution;

public interface SolutionService {

	void createSolution(AccountUserDetail currentUser, Solution solutionModel);

	Solution getStudentSolution(String problemId, String userId);

	boolean submitChallenge(String challengeId, AccountUserDetail currentUser, Solution[] completeSolution);

}
