package com.edu.yati.controller.solution;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.model.Solution;
import com.edu.yati.service.solution.SolutionService;

@RestController
@RequestMapping("/api/user/student/solution")
public class SolutionController {
	
	private Logger logger = LoggerFactory.getLogger(SolutionController.class);
	
	@Autowired
	SolutionService solutionService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> createSolution(@RequestBody Solution solutionModel) {
		AccountUserDetail currentUser = (AccountUserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		solutionService.createSolution(currentUser, solutionModel);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
	
	@RequestMapping(value= "/solution", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Solution> getStudentSolution(@RequestParam("problemId") String problemId,
			@RequestParam(value="userId", required=false) String userId) {
		
		AccountUserDetail currentUser = (AccountUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userId == null) {
			userId = currentUser.getId();
		}
		
		Solution solution = solutionService.getStudentSolution(problemId, userId);
		if(solution == null) {
			solution = new Solution();
		}
		return new ResponseEntity<Solution>(solution, HttpStatus.OK);
	}
	
	@RequestMapping(value="/submit_challenge/{challengeId}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> submitChallenge(@PathVariable("challengeId")String challengeId,
			@RequestBody Solution[] completeSolution) {
		logger.debug("completeSolution " + Arrays.toString(completeSolution));
		AccountUserDetail currentUser = (AccountUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean submitted = solutionService.submitChallenge(challengeId, currentUser, completeSolution);
		return new ResponseEntity<Boolean>(submitted, HttpStatus.OK);
	}
	
}
