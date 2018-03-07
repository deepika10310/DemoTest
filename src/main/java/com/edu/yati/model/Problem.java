package com.edu.yati.model;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Problem implements Serializable {
	
	@Id
    @JsonProperty
    private String id;
	private static final long serialVersionUID = -5674949878443447878L;
	private ProblemType questionType;
	private int score;
	private int penalty;
	private String challengeId;
	private MatrixProblem matrixProblem;
	private IntegerProblem integerProblem;
	private SingleMoreProblem singleMoreProblem;
	private Bucket[] buckets;
	
	public int getPenalty() {
		return penalty;
	}
	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}
	public ProblemType getQuestionType() {
		return questionType;
	}
	public void setQuestionType(ProblemType questionType) {
		this.questionType = questionType;
	}
	public MatrixProblem getMatrixProblem() {
		return matrixProblem;
	}
	public void setMatrixProblem(MatrixProblem matrixProblem) {
		this.matrixProblem = matrixProblem;
	}
	public IntegerProblem getIntegerProblem() {
		return integerProblem;
	}
	public void setIntegerProblem(IntegerProblem integerProblem) {
		this.integerProblem = integerProblem;
	}
	public SingleMoreProblem getSingleMoreProblem() {
		return singleMoreProblem;
	}
	public void setSingleMoreProblem(SingleMoreProblem singleMoreProblem) {
		this.singleMoreProblem = singleMoreProblem;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getChallengeId() {
		return challengeId;
	}
	public void setChallengeId(String challengeId) {
		this.challengeId = challengeId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Bucket[] getBuckets() {
		return buckets;
	}
	public void setBuckets(Bucket[] buckets) {
		this.buckets = buckets;
	}
	@Override
	public String toString() {
		return "Problem [id=" + id + ", questionType=" + questionType + ", score=" + score + ", penalty=" + penalty
				+ ", challengeId=" + challengeId + ", matrixProblem=" + matrixProblem + ", integerProblem="
				+ integerProblem + ", singleMoreProblem=" + singleMoreProblem + ", buckets=" + Arrays.toString(buckets)
				+ "]";
	}
}
