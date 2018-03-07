package com.edu.yati.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProblemType {
	@JsonProperty("INTEGER_TYPE")
	INTEGER_TYPE,
	@JsonProperty("MATRIX_MATCH_TYPE")
	MATRIX_MATCH_TYPE,
    @JsonProperty("SINGLE_MORE_TYPE")
	SINGLE_MORE_TYPE
}
