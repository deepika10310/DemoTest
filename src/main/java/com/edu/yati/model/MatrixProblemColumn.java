package com.edu.yati.model;

import java.io.Serializable;

public class MatrixProblemColumn implements Serializable {
	private static final long serialVersionUID = 4510495598818469951L;
	String label;
	String value;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}