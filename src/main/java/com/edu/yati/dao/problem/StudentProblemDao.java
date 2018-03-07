package com.edu.yati.dao.problem;

import java.util.List;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.model.ChallengeScore;
import com.edu.yati.model.Problem;
import com.edu.yati.model.StudentTimer;

public interface StudentProblemDao {

	List<Problem> listAllProblems(AccountUserDetail currentUser, String challengeId);

	public StudentTimer getChallengeStudentTimer(String challengeId, AccountUserDetail currentUser);
	
	public boolean submitTest(String challengeId, AccountUserDetail currentUser);

	boolean checkFinished(String challengeId, AccountUserDetail currentUser);

	List<ChallengeScore> getChallengeScore(String challengeId, AccountUserDetail currentUser);

}
