package com.ngeso.security.model;

public class Token {
	//client_name
	private final String mpId;
	//client_guid
	private final String mpGuid;
	//client_id
	private final String clientId;
	//aud
	private final String aud;
	//iss https://identity.oraclecloud.com
	private final String  iss;
	//scope
	private final String  scope;
	//exp
	private final String expiresAt;
	//iat
	private final String issuedAt;

	public String getMpId() {
		return mpId;
	}
	public String getMpGuid() {
		return mpGuid;
	}
	public String getClientId() {
		return clientId;
	}
	public String getAud() {
		return aud;
	}
	public String getIss() {
		return iss;
	}
	public String getScope() {
		return scope;
	}
	public String getExpiresAt() {
		return expiresAt;
	}
	public String getIssuedAt() {
		return issuedAt;
	}
	private Token(String mpId, String mpGuid, String clientId, String aud, String iss, String scope,
			String expiresAt, String issuedAt) {
		super();
		this.mpId = mpId;
		this.mpGuid = mpGuid;
		this.clientId = clientId;
		this.aud = aud;
		this.iss = iss;
		this.scope = scope;
		this.expiresAt = expiresAt;
		this.issuedAt = issuedAt;
	}

	public static class Builder{
		//client_name
		private String mpId;
		//client_guid
		private String mpGuid;
		//client_id
		private String clientId;
		//aud
		private String aud;
		//iss https://identity.oraclecloud.com
		private String  iss;
		//scope
		private String  scope;
		//exp
		private String expiresAt;
		//iat
		private String issuedAt;

		//client_name
		public Builder mpId(String mpID) {
			this.mpId=mpID;
			return this;
		}
		//client_guid
		public Builder mpGuid(String mpGuid) {
			this.mpGuid=mpGuid;
			return this;
		}
		//client_id
		public Builder clientId(String clientId) {
			this.clientId=clientId;
			return this;
		}
		//aud
		public Builder aud(String aud) {
			this.aud=aud;
			return this;
		}
		//iss https://identity.oraclecloud.com
		public Builder iss(String iss) {
			this.iss=iss;
			return this;
		}
		//scope
		public Builder scope(String scope) {
			this.scope=scope;
			return this;
		}

		//exp
		public Builder expiresAt(String expiresAt) {
			this.expiresAt = expiresAt;
			//final Instant instant = Instant.ofEpochSecond(Long.parseLong(expiresAt));
			//this.expiresAt = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
			return this;
		}
		//iat
		public Builder issuedAt(String issuedAt) {
			this.expiresAt = issuedAt;
			//final Instant instant = Instant.ofEpochSecond(Long.parseLong(issuedAt));
			//this.issuedAt = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
			return this;
		}

		public Token build() {
			return new Token(mpId, mpGuid, clientId, aud, iss, scope, expiresAt, issuedAt);
		}

	}
}
