package com.ngeso.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.ngeso.security.exception.TokenInvalidException;
import com.ngeso.security.exception.UnknownEnvironmentException;

public class ExceptionTest {
	@Test
	public void TokenInvalidException() {

		final TokenInvalidException se =new TokenInvalidException("blahblah");

		assertThat(se.getMessage()).isSameAs("blahblah");
	}

	@Test
	public void UnknownEnvironmentException() {

		final UnknownEnvironmentException se =new UnknownEnvironmentException ("blahblah");

		assertThat(se.getMessage()).isSameAs("blahblah");
	}
}
