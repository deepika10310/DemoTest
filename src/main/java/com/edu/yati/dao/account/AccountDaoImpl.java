package com.edu.yati.dao.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.edu.yati.auth.security.custom.AccountUserDetail;

@Repository
public class AccountDaoImpl implements AccountDao {

	private static final String EDUCATION_ACCOUNT = "EDUCATION_ACCOUNT";
	
	@Autowired 
    private MongoTemplate mongoTemplate_;
    //private static final String ACCOUNT_SEQ_KEY = "ACCOUNT_SEQ_KEY";
    
    /*@Autowired
	private SequenceDao sequenceDao;*/
	
    /*@Autowired
    public AccountDaoImpl(final MongoTemplate mongoTemplate) {
        mongoTemplate_ = mongoTemplate;
    }*/
    
	@Override
	public List<AccountUserDetail> listAllUsers() {
		final Query q = new Query();
        final List<AccountUserDetail> result = mongoTemplate_.find(q, AccountUserDetail.class,
        		EDUCATION_ACCOUNT);
        return result;
	}

	@Override
	public AccountUserDetail getAccountUserDetails(long id) {
		final Query q = new Query().addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate_.findOne(q, AccountUserDetail.class, EDUCATION_ACCOUNT);
	}

	@Override
	public AccountUserDetail createAccountUser(AccountUserDetail accountUser) {
		//accountUser.setId(sequenceDao.getNextSequenceId(ACCOUNT_SEQ_KEY));
		mongoTemplate_.insert(accountUser, EDUCATION_ACCOUNT);
        return accountUser;
	}

	@Override
	public AccountUserDetail updateAccountUser(String username, AccountUserDetail accountUser) {
		final Query q = new Query().addCriteria(Criteria.where("username").is(username));
        final Update update = Update.update("username", accountUser.getUsername());
        //update.set("password", accountUser.getPassword());
        update.set("firstName", accountUser.getFirstName());
        update.set("lastName", accountUser.getLastName());
        update.set("email", accountUser.getEmail());
        update.set("roles", accountUser.getRoles());
        update.set("accountNonExpired", accountUser.isAccountNonExpired());
        update.set("accountNonLocked", accountUser.isAccountNonLocked());
        update.set("credentialsNonExpired", accountUser.isCredentialsNonExpired());
        update.set("enabled", accountUser.isEnabled());
        mongoTemplate_.updateFirst(q, update, AccountUserDetail.class, EDUCATION_ACCOUNT);
        return accountUser;
	}

	@Override
	public AccountUserDetail deleteAccountUser(long id) {
		return null;
	}

	@Override
	public AccountUserDetail deleteAllAccountUser() {
		return null;
	}

	@Override
	public boolean enableAccountUser(boolean enable, long id) {
		final Query q = new Query().addCriteria(Criteria.where("_id").is(id));
        final Update update = Update.update("id", id);
        update.set("enabled", enable);
        mongoTemplate_.updateFirst(q, update, AccountUserDetail.class, EDUCATION_ACCOUNT);
        return true;
	}

	@Override
	public boolean isUserExist(AccountUserDetail accountUserDetail) {
		final Query q = new Query().addCriteria(Criteria.where("username").is(accountUserDetail.getUsername()));
		return mongoTemplate_.findOne(q, AccountUserDetail.class, EDUCATION_ACCOUNT) != null;
	}

	@Override
	public AccountUserDetail getAccountUserDetails(String username) {
		final Query q = new Query().addCriteria(Criteria.where("username").is(username));
        return mongoTemplate_.findOne(q, AccountUserDetail.class, EDUCATION_ACCOUNT);
	}
}
