
package com.edu.yati.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.dao.account.AccountDao;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	
	private List<AccountUserDetail> updateUserAdapter(String fieldName, String fieldValue, List<AccountUserDetail> accountUserDetails) {
		for(AccountUserDetail accountUserDetail : accountUserDetails) {
			switch (fieldName) {
			case "password":
				accountUserDetail.setPassword(fieldValue);
				break;

			default:
				break;
			}
		}
		return accountUserDetails;
	}
	
	@Override
	public List<AccountUserDetail> listAllUsers() {
		return updateUserAdapter("password", null, accountDao.listAllUsers());
	}
	
	@Override
	public AccountUserDetail getAccountUserDetails(String userName) {
		return accountDao.getAccountUserDetails(userName);
	}

	@Override
	public AccountUserDetail getAccountUserDetails(long id) {
		return accountDao.getAccountUserDetails(id);
	}

	@Override
	public void createAccountUser(AccountUserDetail accountUser) {
		accountDao.createAccountUser(accountUser);
	}

	@Override
	public AccountUserDetail updateAccountUser(String username, AccountUserDetail accountUser) {
		return accountDao.updateAccountUser(username, accountUser);
	}

	@Override
	public AccountUserDetail deleteAccountUser(long id) {
		return accountDao.deleteAccountUser(id);
	}

	@Override
	public AccountUserDetail deleteAllAccountUser() {
		return accountDao.deleteAllAccountUser();
	}

	@Override
	public boolean enableAccountUser(boolean enable, long id) {
		return accountDao.enableAccountUser(enable, id);
	}

	@Override
	public boolean isUserExist(AccountUserDetail accountUserDetail) {
		
		return accountDao.isUserExist(accountUserDetail);
	}

}
