package com.edu.yati.service.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.dao.solution.SolutionDao;
import com.edu.yati.model.Solution;

@Service
public class SolutionServiceImpl implements SolutionService {

	@Autowired 
	SolutionDao solutionDao;
	
	@Override
	public void createSolution(AccountUserDetail currentUser, Solution solutionModel) {
		solutionDao.createSolution(currentUser,solutionModel);
	}

	@Override
	public Solution getStudentSolution(String problemId, String userId) {
		return solutionDao.getStudentSolution(problemId, userId);
	}

	@Override
	public boolean submitChallenge(String challengeId, AccountUserDetail currentUser,
			Solution[] completeSolution) {
		return solutionDao.submitChallenge(challengeId, currentUser, completeSolution);
	}

}
