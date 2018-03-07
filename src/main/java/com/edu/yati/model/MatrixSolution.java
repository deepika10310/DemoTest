package com.edu.yati.model;

import java.io.Serializable;
import java.util.Arrays;

public class MatrixSolution implements Serializable {
	private static final long serialVersionUID = 8038538247851940270L;
	private String leftColumn;
	private String[] rightColumn;
	public String getLeftColumn() {
		return leftColumn;
	}
	public void setLeftColumn(String leftColumn) {
		this.leftColumn = leftColumn;
	}
	public String[] getRightColumn() {
		return rightColumn;
	}
	public void setRightColumn(String[] rightColumn) {
		this.rightColumn = rightColumn;
	}
	@Override
	public String toString() {
		return "MatrixSolution [leftColumn=" + leftColumn + ", rightColumn=" + Arrays.toString(rightColumn) + "]";
	}
	
	
}
