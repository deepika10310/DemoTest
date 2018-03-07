package com.edu.yati.dao.challenge;

import java.util.List;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.model.Challenge;

public interface ChallengeDao {

	public List<Challenge> listAllChallenges();

	public Challenge getChallengeDetails(String challengeName);

	public boolean isChallengeExist(Challenge challengeModel);

	public Challenge createChallenge(Challenge challengeModel);

	public Challenge updateChallenge(String challengeName, Challenge challengeModel);

	public void deleteChallenge(String id);

	public void deleteAllChallenges();

	public List<Challenge> listAllCurrentUserChallenges(AccountUserDetail currentUser);

}
