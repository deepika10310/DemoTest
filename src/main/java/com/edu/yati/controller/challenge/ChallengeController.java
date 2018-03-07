package com.edu.yati.controller.challenge;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edu.yati.model.Challenge;
import com.edu.yati.service.challenge.ChallengeService;

@RestController
@RequestMapping("/api/admin/challenge")
public class ChallengeController {
	Logger logger = LoggerFactory.getLogger(ChallengeController.class);

	@Autowired
	private ChallengeService challengeService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Challenge>> listAllChallengeDetail() {
        List<Challenge> challengeDetails = challengeService.listAllChallenges();
        logger.debug("listAllChallengeDetail " + challengeDetails);
        if(challengeDetails.isEmpty()){
            return new ResponseEntity<List<Challenge>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Challenge>>(challengeDetails, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Challenge> getChallengeDetails(@PathVariable("id") String id) {
    	logger.debug("Fetching Challenge with id " + id);
    	Challenge challenge = challengeService.getChallengeDetails(id);
    	logger.debug("getChallengeDetails " + challenge);
        if (challenge == null) {
            System.out.println("Challenge with id " + id + " not found");
            return new ResponseEntity<Challenge>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Challenge>(challenge, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> createChallenge(@RequestBody Challenge challengeModel) {
    	/*logger.debug("Creating Challenge " + challengeModel.getId());
        if (challengeService.isChallengeExist(challengeModel)) {
        	logger.debug("A challenge with name " + challengeModel.getId() + " already exist");
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        }*/
        challengeService.createChallenge(challengeModel);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Challenge> updateChallenge(@PathVariable("id") String id, @RequestBody Challenge challengeModel) {
    	logger.debug("Updating challenge " + id);

    	Challenge prevChallengeModel = challengeService.getChallengeDetails(id);
    	logger.debug("updateChallenge " + prevChallengeModel);
        if (prevChallengeModel == null) {
        	logger.debug("Challenge with id " + id + " not found");
        	
            return new ResponseEntity<Challenge>(HttpStatus.NOT_FOUND);
        }
        challengeService.updateChallenge(id, challengeModel);
        return new ResponseEntity<Challenge>(challengeModel, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Challenge> deleteChallenge(@PathVariable("id") String id) {
    	logger.debug("Fetching & Deleting Challenge with id " + id);
 
    	Challenge challenge = challengeService.getChallengeDetails(id);
        if (challenge == null) {
        	logger.debug("Unable to delete. Challenge with id " + id + " not found");
            return new ResponseEntity<Challenge>(HttpStatus.NOT_FOUND);
        }
 
        challengeService.deleteChallenge(id);
        return new ResponseEntity<Challenge>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/challenge/", method = RequestMethod.DELETE)
    public ResponseEntity<Challenge> deleteAllChallenges() {
    	logger.debug("Deleting All Challenges");
    	challengeService.deleteAllChallenges();
        return new ResponseEntity<Challenge>(HttpStatus.NO_CONTENT);
    }
	
}
