package com.edu.yati.controller.problem;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.yati.model.Problem;
import com.edu.yati.model.ProblemType;
import com.edu.yati.service.problem.ProblemService;

@RestController
@RequestMapping("/api/admin/problem")
public class ProblemController {
	Logger logger = LoggerFactory.getLogger(ProblemController.class);
	
	@Autowired
	private ProblemService problemService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Problem>> listAllProblems(@RequestParam(value="challengeId", required=true) String challengeId) {
        List<Problem> problems = problemService.listAllProblems(challengeId);
        logger.debug("listAllProblems " + problems);
        if(problems.isEmpty()){
            return new ResponseEntity<List<Problem>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Problem>>(problems, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Problem> getProblemDetails(@PathVariable("id") String id) {
    	logger.debug("Fetching Problem with id " + id);
    	Problem problem = problemService.getProblemDetails(id);
        if (problem == null) {
        	logger.debug("Problem with id " + id + " not found");
            return new ResponseEntity<Problem>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Problem>(problem, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> createProblem(@RequestBody Problem problemModel) {
		logger.debug(" problem statement " + problemModel);
		problemService.createProblem(problemModel);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public ResponseEntity<Problem> updateProblem(@RequestBody Problem problemModel) {
    	logger.debug("Updating problem " + problemModel.getId());

    	Problem prevProblem = problemService.getProblemDetails(problemModel.getId());
        if (prevProblem == null) {
        	logger.debug("Problem with id " + problemModel.getId() + " not found");
        	
            return new ResponseEntity<Problem>(HttpStatus.NOT_FOUND);
        }
        problemService.updateProblem(problemModel);
        return new ResponseEntity<Problem>(problemModel, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Problem> deleteProblem(@PathVariable("id") String id) {
    	logger.debug("Fetching & Deleting Problem with id " + id);
 
    	Problem problem = problemService.getProblemDetails(id);
        if (problem == null) {
        	logger.debug("Unable to delete. Problem with id " + id + " not found");
            return new ResponseEntity<Problem>(HttpStatus.NOT_FOUND);
        }
 
        problemService.deleteProblem(id);
        return new ResponseEntity<Problem>(HttpStatus.NO_CONTENT);
    }
	
	@RequestMapping(value = "/problem_type", method = RequestMethod.GET)
    public ResponseEntity<List<ProblemType>> getProblemTypes(@PathVariable("id") String id) {
		List<ProblemType> problemTypeList = new ArrayList<>();
		problemTypeList.add(ProblemType.INTEGER_TYPE);
		problemTypeList.add(ProblemType.MATRIX_MATCH_TYPE);
		problemTypeList.add(ProblemType.SINGLE_MORE_TYPE);
        return new ResponseEntity<List<ProblemType>>(problemTypeList, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/count/{challengeId}", method = RequestMethod.GET)
    public ResponseEntity<Long> getProblemCount(@PathVariable("challengeId") String challengeId) {
		long problemCount = problemService.getProblemCount(challengeId);
        return new ResponseEntity<Long>(problemCount, HttpStatus.OK);
    }
	/*
    @RequestMapping(value = "/challenge/", method = RequestMethod.DELETE)
    public ResponseEntity<Challenge> deleteAllChallenges() {
    	logger.debug("Deleting All Challenges");
    	challengeService.deleteAllChallenges();
        return new ResponseEntity<Challenge>(HttpStatus.NO_CONTENT);
    }*/
}
