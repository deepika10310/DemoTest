package com.edu.yati.service.challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.dao.challenge.ChallengeDao;
import com.edu.yati.model.Challenge;

@Service
public class ChallengeServiceImpl implements ChallengeService {

	@Autowired
	private ChallengeDao challengeDao;
	
	@Override
	public List<Challenge> listAllChallenges() {
		return challengeDao.listAllChallenges();
	}

	@Override
	public Challenge getChallengeDetails(String id) {
		return challengeDao.getChallengeDetails(id);
	}

	@Override
	public boolean isChallengeExist(Challenge challengeModel) {
		return challengeDao.isChallengeExist(challengeModel);
	}

	@Override
	public Challenge createChallenge(Challenge challengeModel) {
		return challengeDao.createChallenge(challengeModel);
	}

	@Override
	public Challenge updateChallenge(String id, Challenge challengeModel) {
		return challengeDao.updateChallenge(id, challengeModel);
		
	}

	@Override
	public void deleteChallenge(String id) {
		challengeDao.deleteChallenge(id);
	}

	@Override
	public void deleteAllChallenges() {
		challengeDao.deleteAllChallenges();
	}

	@Override
	public List<Challenge> listAllCurrentUserChallenges(AccountUserDetail currentUser) {
		return challengeDao.listAllCurrentUserChallenges(currentUser);
	}

}
