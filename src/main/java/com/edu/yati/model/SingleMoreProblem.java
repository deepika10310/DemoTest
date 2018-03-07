package com.edu.yati.model;

import java.io.Serializable;
import java.util.Set;

public class SingleMoreProblem implements Serializable {
	private static final long serialVersionUID = 7180472133083301382L;
	private String problemStatement;
	private Set<String> correctOptions;
	private SingleMoreProblemOption[] problemOptions;

	public String getProblemStatement() {
		return problemStatement;
	}

	public void setProblemStatement(String problemStatement) {
		this.problemStatement = problemStatement;
	}

	public Set<String> getCorrectOptions() {
		return correctOptions;
	}

	public void setCorrectOptions(Set<String> correctOptions) {
		this.correctOptions = correctOptions;
	}

	public SingleMoreProblemOption[] getProblemOptions() {
		return problemOptions;
	}

	public void setProblemOptions(SingleMoreProblemOption[] problemOptions) {
		this.problemOptions = problemOptions;
	}
	
	
}
