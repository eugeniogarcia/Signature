package com.ngeso.security.model;

public class VerifyResult {
	private final String message;
	private final String errorCode;
	private final boolean isValid;

	private VerifyResult(String message, String errorCode, boolean isValid) {
		super();
		this.message = message;
		this.errorCode = errorCode;
		this.isValid = isValid;
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
		private String message="";
		private String errorCode="";
		private boolean isValid=false;

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

		public VerifyResult build() {
			return new VerifyResult(this.message,this.errorCode, this.isValid);
		}

	}

}
