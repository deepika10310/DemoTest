package com.edu.yati.controller.challenge;

import java.util.List;

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
import com.edu.yati.model.Challenge;
import com.edu.yati.service.challenge.ChallengeService;

@RestController
@RequestMapping("/api/user/student/challenge")
public class StudentChallengeController {
	Logger logger = LoggerFactory.getLogger(StudentChallengeController.class);
	@Autowired
	private ChallengeService challengeService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Challenge>> listAllCurrentUserChallenges() {
		AccountUserDetail currentUser = (AccountUserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Challenge> challengeDetails = challengeService.listAllCurrentUserChallenges(currentUser);
        logger.debug("listAllCurrentUserChallenges " + challengeDetails);
        if(challengeDetails.isEmpty()){
            return new ResponseEntity<List<Challenge>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Challenge>>(challengeDetails, HttpStatus.OK);
    }
	
}
