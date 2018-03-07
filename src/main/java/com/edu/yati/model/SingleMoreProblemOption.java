package com.edu.yati.model;

import java.io.Serializable;

public class SingleMoreProblemOption implements Serializable {
	private static final long serialVersionUID = -5571121015029510176L;
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
	@Override
	public String toString() {
		return "SingleMoreProblemOption [label=" + label + ", value=" + value + "]";
	}
	
	
	
}
