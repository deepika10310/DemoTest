package com.edu.yati.service.problem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.yati.dao.problem.ProblemDao;
import com.edu.yati.model.Problem;

@Service
public class ProblemServiceImpl implements ProblemService {
	
	@Autowired
	private ProblemDao problemDao;

	@Override
	public List<Problem> listAllProblems(String challengeId) {
		
		return problemDao.listAllProblems(challengeId);
	}

	@Override
	public Problem getProblemDetails(String problemId) {
		
		return problemDao.getProblemDetails(problemId);
	}

	@Override
	public void createProblem(Problem problemModel) {
		problemDao.createProblem(problemModel);
		
	}

	@Override
	public void updateProblem(Problem problemModel) {
		problemDao.updateProblem(problemModel);
		
	}

	@Override
	public void deleteProblem(String problemId) {
		problemDao.deleteProblem(problemId);
		
	}

	@Override
	public long getProblemCount(String challengeId) {
		return problemDao.getProblemCount(challengeId);
	}

}
