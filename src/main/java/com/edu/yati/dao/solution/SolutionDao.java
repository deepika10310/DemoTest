package com.edu.yati.dao.solution;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.model.Solution;

public interface SolutionDao {

	void createSolution(AccountUserDetail currentUser, Solution solutionModel);

	Solution getStudentSolution(String problemId, String userId);

	boolean submitChallenge(String challengeId, AccountUserDetail currentUser, Solution[] completeSolution);

}
