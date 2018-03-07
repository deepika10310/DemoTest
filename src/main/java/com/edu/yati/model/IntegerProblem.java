package com.edu.yati.model;

import java.io.Serializable;

public class IntegerProblem implements Serializable {
	private static final long serialVersionUID = 7345608416081154799L;
	private String problemStatement;
	private String[] solution;
	public String getProblemStatement() {
		return problemStatement;
	}
	public void setProblemStatement(String problemStatement) {
		this.problemStatement = problemStatement;
	}
	public String[] getSolution() {
		return solution;
	}
	public void setSolution(String[] solution) {
		this.solution = solution;
	}
	
	
}
