package com.edu.yati.controller.problem;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.yati.auth.security.custom.AccountUserDetail;
import com.edu.yati.model.ChallengeScore;
import com.edu.yati.model.Problem;
import com.edu.yati.model.StudentTimer;
import com.edu.yati.service.problem.StudentProblemService;

@RestController
@RequestMapping("/api/user/student/problem")
public class StudentProblemController {
	Logger logger = LoggerFactory.getLogger(StudentProblemController.class);
	@Autowired
	private StudentProblemService studentProblemService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Problem>> listAllCurrentUserProblems(@RequestParam String challengeId) {
		AccountUserDetail currentUser = (AccountUserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Problem> problemList = studentProblemService.listAllProblems(currentUser, challengeId);
        if(problemList.isEmpty()){
            return new ResponseEntity<List<Problem>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Problem>>(problemList, HttpStatus.OK);
    }
	
	@RequestMapping(value="/start_time/{challengeId}", method = RequestMethod.GET)
	public ResponseEntity<StudentTimer> getUserChallengeStartTime(@PathVariable("challengeId") String challengeId) {
		AccountUserDetail currentUser = (AccountUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		StudentTimer studentTimer = studentProblemService.getChallengeStudentTimer(challengeId, currentUser);
		return new ResponseEntity<StudentTimer>(studentTimer, HttpStatus.OK);
	}
	
	@RequestMapping(value="/submit_test/{challengeId}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> submitTest(@PathVariable("challengeId")String challengeId) {
		AccountUserDetail currentUser = (AccountUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean submitted = studentProblemService.submitTest(challengeId, currentUser);
		return new ResponseEntity<Boolean>(submitted, HttpStatus.OK);
	}
	
	@RequestMapping(value="/check_finished/{challengeId}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkFinished(@PathVariable("challengeId")String challengeId) {
		AccountUserDetail currentUser = (AccountUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean submitted = studentProblemService.checkFinished(challengeId, currentUser);
		return new ResponseEntity<Boolean>(submitted, HttpStatus.OK);
	}
	
	@RequestMapping(value="/score/{challengeId}", method = RequestMethod.GET)
	public ResponseEntity<List<ChallengeScore>> getChallengeScore(@PathVariable("challengeId") String challengeId) {
		AccountUserDetail currentUser = (AccountUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<ChallengeScore> challengeScoreList = studentProblemService.getChallengeScore(challengeId, currentUser);
		logger.debug("challenge score list:::: " + challengeScoreList);
		return new ResponseEntity<List<ChallengeScore>>(challengeScoreList, HttpStatus.OK);
	}
	
}
