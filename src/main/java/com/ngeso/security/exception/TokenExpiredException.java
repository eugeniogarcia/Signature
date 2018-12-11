package com.ngeso.security.exception;

public class TokenExpiredException extends TokenInvalidException {

	/**
	 *
	 */
	private static final long serialVersionUID = -935220744931194246L;

	public TokenExpiredException(final Throwable cause) {
		super(cause);
	}
}
