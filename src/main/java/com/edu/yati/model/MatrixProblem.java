package com.edu.yati.model;

import java.io.Serializable;
import java.util.Set;

public class MatrixProblem implements Serializable {
	private static final long serialVersionUID = 2006484575037225287L;
	private MatrixProblemColumn[] leftColumn;
	private MatrixProblemColumn[] rightColumn;
	private Set<String> correctOptions;
	private String problemStatement;
	public MatrixProblemColumn[] getLeftColumn() {
		return leftColumn;
	}
	public void setLeftColumn(MatrixProblemColumn[] leftColumn) {
		this.leftColumn = leftColumn;
	}
	public MatrixProblemColumn[] getRightColumn() {
		return rightColumn;
	}
	public void setRightColumn(MatrixProblemColumn[] rightColumn) {
		this.rightColumn = rightColumn;
	}
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
}
