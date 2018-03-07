package com.edu.yati.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Solution implements Serializable {
	private static final long serialVersionUID = 1556145900437228560L;
	@Id
    @JsonProperty
    private String id;
	private String username;
	private String userId;
	private String integerSolution;
	private Set<String> singleMoreSolution;
	private Set<String> matrixSolution;
	private String problemId;
	private double marksObtained;
	private String challengeId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProblemId() {
		return problemId;
	}
	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}
	public double getMarksObtained() {
		return marksObtained;
	}
	public void setMarksObtained(double marksObtained) {
		this.marksObtained = marksObtained;
	}
	public String getChallengeId() {
		return challengeId;
	}
	public void setChallengeId(String challengeId) {
		this.challengeId = challengeId;
	}
	public String getIntegerSolution() {
		return integerSolution;
	}
	public void setIntegerSolution(String integerSolution) {
		this.integerSolution = integerSolution;
	}
	public Set<String> getSingleMoreSolution() {
		return singleMoreSolution;
	}
	public void setSingleMoreSolution(Set<String> singleMoreSolution) {
		this.singleMoreSolution = singleMoreSolution;
	}
	public Set<String> getMatrixSolution() {
		return matrixSolution;
	}
	public void setMatrixSolution(Set<String> matrixSolution) {
		this.matrixSolution = matrixSolution;
	}
	@Override
	public String toString() {
		return "Solution [id=" + id + ", username=" + username + ", userId=" + userId + ", integerSolution="
				+ integerSolution + ", singleMoreSolution=" + singleMoreSolution + ", matrixSolution=" + matrixSolution
				+ ", problemId=" + problemId + ", marksObtained=" + marksObtained + ", challengeId=" + challengeId
				+ "]";
	}
	
	
	
}
