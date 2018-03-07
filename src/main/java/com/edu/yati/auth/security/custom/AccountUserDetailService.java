package com.edu.yati.auth.security.custom;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.yati.service.account.AccountService;

@Service
@Transactional
public class AccountUserDetailService implements UserDetailsService {
	private final Logger logger = LoggerFactory.getLogger(AccountUserDetailService.class);
	static Map<String, AccountUserDetail> accountMap = new HashMap<>();
	@Autowired
    private AccountService accountService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;// = new BCryptPasswordEncoder();
    
    public AccountUserDetailService() {
    	/*logger.debug(".......... passwordEncoder " + passwordEncoder);
		String hashPwd = passwordEncoder.encode("bob");
		logger.debug("bob hashPwd is " + passwordEncoder.encode("bob"));
		AccountUserDetail acc1 = new AccountUserDetail("bob", hashPwd);
		acc1.grantAuthority(Role.ROLE_ADMIN);
		AccountUserDetail acc2 = new AccountUserDetail("wills", passwordEncoder.encode("wills"));
		acc2.grantAuthority(Role.ROLE_USER);
		AccountUserDetail acc3 = new AccountUserDetail("jack", passwordEncoder.encode("wills"));
		acc3.grantAuthority(Role.ROLE_USER);
		
		accountMap.put("bob", acc1);
		accountMap.put("wills", acc2);
		accountMap.put("jack", acc3);*/
	}

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	logger.debug("username is ^^^^^ " + userName);
    	//dummy admin
    	
    	logger.debug(".......... passwordEncoder " + passwordEncoder);
		String hashPwd = passwordEncoder.encode("bob");
		logger.debug("bob hashPwd is " + passwordEncoder.encode("bob"));
		AccountUserDetail dummy = new AccountUserDetail("bob", hashPwd);
		dummy.grantAuthority(Role.ROLE_ADMIN);
		if("bob".equals(userName)) {
			logger.debug(dummy.toString());
			return dummy;
		}
    	
    	AccountUserDetail accountUserDetail = accountService.getAccountUserDetails(userName);
        if ( accountUserDetail != null ) {
            return accountUserDetail;
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", userName));
        }
    }
}
