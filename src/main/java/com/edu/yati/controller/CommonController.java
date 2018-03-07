package com.edu.yati.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.service.account.AccountService;

@RestController
@RequestMapping("/common")
public class CommonController {
	Logger logger = LoggerFactory.getLogger(CommonController.class);
	@Autowired
    AccountService accountService;
	
    @RequestMapping("/hello")
    public String home() {
        return "Hello World";
    }
    
    @RequestMapping(value = "/current_user", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AccountUserDetail> getCurrentUserDetails() {
    	AccountUserDetail user = (AccountUserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (user == null) {
            return new ResponseEntity<AccountUserDetail>(HttpStatus.NOT_FOUND);
        }
        user.setPassword(null);
        logger.debug("getCurrentUserDetails :: " + user);
        return new ResponseEntity<AccountUserDetail>(user, HttpStatus.OK);
    }
}
