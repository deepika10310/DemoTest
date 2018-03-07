package com.edu.yati.service.challenge;

import java.util.List;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.model.Challenge;

public interface ChallengeService {

	List<Challenge> listAllChallenges();

	Challenge getChallengeDetails(String challengeName);

	boolean isChallengeExist(Challenge challengeModel);

	Challenge createChallenge(Challenge challengeModel);

	Challenge updateChallenge(String challengeName, Challenge challengeModel);

	void deleteChallenge(String id);

	void deleteAllChallenges();

	List<Challenge> listAllCurrentUserChallenges(AccountUserDetail currentUser);

}
