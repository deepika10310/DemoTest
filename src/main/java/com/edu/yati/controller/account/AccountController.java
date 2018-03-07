package com.edu.yati.controller.account;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.auth.security.custom.Role;
import com.edu.yati.service.account.AccountService;

@RestController
@RequestMapping("/api/admin/account")
public class AccountController {
    Logger logger = LoggerFactory.getLogger(AccountController.class);
    
    @Autowired
    AccountService accountService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<AccountUserDetail>> listAllAccountUserDetail() {
        List<AccountUserDetail> accountUserDetails = accountService.listAllUsers();
        logger.debug("listAllAccountUserDetail :: " + accountUserDetails);
        if(accountUserDetails.isEmpty()){
            return new ResponseEntity<List<AccountUserDetail>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<AccountUserDetail>>(accountUserDetails, HttpStatus.OK);
    }
 
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AccountUserDetail> getAccountUserDetails(@PathVariable("id") long id) {
    	logger.debug("Fetching User with id " + id);
    	AccountUserDetail user = accountService.getAccountUserDetails(id);
    	logger.debug("getAccountUserDetails :: " + user);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<AccountUserDetail>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AccountUserDetail>(user, HttpStatus.OK);
    }
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private List<Role> convertStringRoleToEnum(List<String> roles) {
    	List<Role> roleList = new ArrayList<>();
    	for(String role : roles) {
    		switch (role) {
			case "user":
				roleList.add(Role.ROLE_USER);
				break;
			case "admin":
				roleList.add(Role.ROLE_ADMIN);
				break;

			default:
				break;
			}
    	}
    	return roleList;
    }
    
    @RequestMapping(value = "/add_user", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> createUser(@RequestParam("username") String username, @RequestParam("password") String password,
    								@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
    								@RequestParam("email") String email, @RequestParam(value = "roles", required = false) List<String> roles) {
    	AccountUserDetail accountUserDetail = new AccountUserDetail(username, passwordEncoder.encode(password));
    	accountUserDetail.setFirstName(firstName);
    	accountUserDetail.setLastName(lastName);
    	accountUserDetail.setEmail(email);
    	List<Role> roleList;
    	if(roles != null && roles.size() > 0) {
    		roleList = convertStringRoleToEnum(roles);
    	} else {
    		roleList = new ArrayList<>();
        	roleList.add(Role.ROLE_USER);
    	}
    	accountUserDetail.setRoles(roleList);
    	logger.debug("Creating User " + accountUserDetail.getUsername());
        if (accountService.isUserExist(accountUserDetail)) {
        	logger.debug("A User with name " + accountUserDetail.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        accountService.createAccountUser(accountUserDetail);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> createUser(@RequestBody AccountUserDetail accountUserDetail) {
    	logger.debug("Creating User " + accountUserDetail.getUsername());
    	accountUserDetail.setPassword(passwordEncoder.encode(accountUserDetail.getPassword()));
        if (accountService.isUserExist(accountUserDetail)) {
        	logger.debug("A User with name " + accountUserDetail.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        accountService.createAccountUser(accountUserDetail);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value = "/{username}", method = RequestMethod.PUT)
    public ResponseEntity<AccountUserDetail> updateUser(@PathVariable("username") String username, @RequestBody AccountUserDetail accountUserDetail) {
    	logger.debug("Updating User " + username);

    	AccountUserDetail currentAccountUserDetail = accountService.getAccountUserDetails(username);
        if (currentAccountUserDetail == null) {
        	logger.debug("User with username " + username + " not found");
        	
            return new ResponseEntity<AccountUserDetail>(HttpStatus.NOT_FOUND);
        }
        accountService.updateAccountUser(username, accountUserDetail);
        return new ResponseEntity<AccountUserDetail>(accountUserDetail, HttpStatus.OK);
    }
 
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AccountUserDetail> deleteUser(@PathVariable("id") long id) {
    	logger.debug("Fetching & Deleting User with id " + id);
 
        AccountUserDetail user = accountService.getAccountUserDetails(id);
        if (user == null) {
        	logger.debug("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<AccountUserDetail>(HttpStatus.NOT_FOUND);
        }
 
        accountService.deleteAccountUser(id);
        return new ResponseEntity<AccountUserDetail>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<AccountUserDetail> deleteAllUsers() {
    	logger.debug("Deleting All Users");
        accountService.deleteAllAccountUser();
        return new ResponseEntity<AccountUserDetail>(HttpStatus.NO_CONTENT);
    }
}
