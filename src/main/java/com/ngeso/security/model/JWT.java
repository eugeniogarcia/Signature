package com.ngeso.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JWT {
	@JsonProperty("access_token")
	private String access_token ;

	@JsonProperty("token_type")
	private String token_type ;

	@JsonProperty("expires_in")
	private String expires_in;

	public JWT access_token(String access_token) {
		this.access_token = access_token;
		return this;
	}

	public JWT token_type(String token_type) {
		this.token_type = token_type;
		return this;
	}

	public JWT expires_in(String expires_in) {
		this.expires_in = expires_in;
		return this;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
}


