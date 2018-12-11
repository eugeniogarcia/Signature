package com.ngeso.security.model;

public class SignatureResult {
	private final String signature;
	private final String message;
	private final String errorCode;
	private final boolean isValid;

	private SignatureResult(String signature,String message, String errorCode, boolean isValid) {
		super();
		this.message = message;
		this.errorCode = errorCode;
		this.isValid = isValid;
		this.signature=signature;
	}

	public String getSignature() {
		return signature;
	}

	public String getMessage() {
		return message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public boolean isValid() {
		return isValid;
	}

	public static class Builder{
		private String signature="";
		private String message="";
		private String errorCode="";
		private boolean isValid=false;

		public Builder signature(String signature) {
			this.signature=signature;
			return this;
		}

		public Builder message(String msg) {
			this.message=msg;
			return this;
		}

		public Builder errorCode(String err) {
			this.errorCode=err;
			return this;
		}

		public Builder isValid(boolean valid) {
			this.isValid=valid;
			return this;
		}

		public SignatureResult build() {
			return new SignatureResult(this.signature,this.message,this.errorCode, this.isValid);
		}

	}

}
