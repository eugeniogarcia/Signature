package com.ngeso.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.ngeso.security.model.Result;

public class SignatureResultTest {
	@Test
	public void SignatureResult1() {
		final Result result=new Result.Builder()
				.errorCode("myError")
				.isValid(false)
				.message("myCode")
				.build();

		assertThat(result.getErrorCode()).isSameAs("myError");
		assertThat(result.getOutput()).isSameAs("");
		assertThat(result.getErrorMessage()).isSameAs("myCode");
		assertThat(result.isValid()).isFalse();

	}

	public void SignatureResult2() {
		final Result result=new Result.Builder()
				.isValid(true)
				.output("firma")
				.build();

		assertThat(result.getErrorCode()).isSameAs("");
		assertThat(result.getOutput()).isSameAs("firma");
		assertThat(result.getErrorMessage()).isSameAs("");
		assertThat(result.isValid()).isTrue();

	}
}
