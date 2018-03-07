package com.edu.yati.auth.security.custom;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
	@JsonProperty("ROLE_USER")
	ROLE_USER,
	@JsonProperty("ROLE_ADMIN")
    ROLE_ADMIN,
    @JsonProperty("ROLE_REGISTER")
    ROLE_REGISTER,
    @JsonProperty("ROLE_TRUSTED_CLIENT")
    ROLE_TRUSTED_CLIENT,
    @JsonProperty("ROLE_CLIENT")
    ROLE_CLIENT
}