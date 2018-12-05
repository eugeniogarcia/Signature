package com.ngeso.security.exception;

public class TokenExpiredException extends TokenInvalidException {

	public TokenExpiredException(final Throwable cause) {
		super(cause);
	}
}
