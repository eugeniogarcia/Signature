package com.ngeso.security.model;

public class Result {
	private final String output;
	private final String errorMessage;
	private final String errorCode;
	private final boolean isValid;

	private Result(String signature,String message, String errorCode, boolean isValid) {
		super();
		this.errorMessage = message;
		this.errorCode = errorCode;
		this.isValid = isValid;
		this.output=signature;
	}

	public String getOutput() {
		return output;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public boolean isValid() {
		return isValid;
	}

	public static class Builder{
		private String output="";
		private String message="";
		private String errorCode="";
		private boolean isValid=false;

		public Builder output(String signature) {
			this.output=signature;
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

		public Result build() {
			return new Result(this.output,this.message,this.errorCode, this.isValid);
		}

	}

}
