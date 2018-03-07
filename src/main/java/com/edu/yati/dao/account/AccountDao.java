package com.edu.yati.dao.account;

import java.util.List;

import com.edu.yati.auth.security.custom.AccountUserDetail;

public interface AccountDao {
	public List<AccountUserDetail> listAllUsers();
	public AccountUserDetail getAccountUserDetails(long id);
	public AccountUserDetail createAccountUser(AccountUserDetail accountUser);
	public AccountUserDetail deleteAccountUser(long id);
	public AccountUserDetail deleteAllAccountUser();
	public boolean enableAccountUser(boolean enable, long id);
	public boolean isUserExist(AccountUserDetail accountUserDetail);
	public AccountUserDetail getAccountUserDetails(String userName);
	AccountUserDetail updateAccountUser(String username, AccountUserDetail accountUser);
}
