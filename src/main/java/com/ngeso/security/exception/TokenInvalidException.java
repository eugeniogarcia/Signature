package com.ngeso.security.exception;

public class TokenInvalidException extends RuntimeException {

	public TokenInvalidException(final String message) {
		super(message);
	}

	public TokenInvalidException(final Throwable cause) {
		super(cause);
	}
}