package com.edu.yati.service.problem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.dao.problem.StudentProblemDao;
import com.edu.yati.model.ChallengeScore;
import com.edu.yati.model.Problem;
import com.edu.yati.model.StudentTimer;

@Service
public class StudentProblemServiceImpl implements StudentProblemService {

	@Autowired
	StudentProblemDao studentProblemDao;
	
	@Override
	public List<Problem> listAllProblems(AccountUserDetail currentUser, String challengeId) {
		
		return studentProblemDao.listAllProblems(currentUser, challengeId);
	}

	@Override
	public StudentTimer getChallengeStudentTimer(String challengeId, AccountUserDetail currentUser) {
		
		return studentProblemDao.getChallengeStudentTimer(challengeId, currentUser);
	}

	@Override
	public boolean submitTest(String challengeId, AccountUserDetail currentUser) {
		return studentProblemDao.submitTest(challengeId, currentUser);
	}

	@Override
	public boolean checkFinished(String challengeId, AccountUserDetail currentUser) {
		return studentProblemDao.checkFinished(challengeId, currentUser);
	}

	@Override
	public List<ChallengeScore> getChallengeScore(String challengeId, AccountUserDetail currentUser) {
		return studentProblemDao.getChallengeScore(challengeId, currentUser);
	}

}
