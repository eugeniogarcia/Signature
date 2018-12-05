package com.ngeso.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.ngeso.security.model.VerifyResult;

public class VerifyResultTest {
	@Test
	public void VerifyResults1() {
		final VerifyResult result=new VerifyResult.Builder()
				.errorCode("myError")
				.isValid(true)
				.message("myCode")
				.build();

		assertThat(result.getErrorCode()).isSameAs("myError");
		assertThat(result.getMessage()).isSameAs("myCode");
		assertThat(result.isValid()).isTrue();

	}

	public void VerifyResults2() {
		final VerifyResult result=new VerifyResult.Builder()
				.isValid(false)
				.errorCode("myError")
				.message("myCode")
				.build();

		assertThat(result.getErrorCode()).isSameAs("myError");
		assertThat(result.getMessage()).isSameAs("myCode");
		assertThat(result.isValid()).isFalse();

	}
	public void VerifyResults3() {
		final VerifyResult result=new VerifyResult.Builder()
				.isValid(false)
				.build();

		assertThat(result.getErrorCode()).isSameAs("");
		assertThat(result.getMessage()).isSameAs("");
		assertThat(result.isValid()).isFalse();

	}
	public void VerifyResults4() {
		final VerifyResult result=new VerifyResult.Builder()
				.isValid(true)
				.build();

		assertThat(result.getErrorCode()).isSameAs("");
		assertThat(result.getMessage()).isSameAs("");
		assertThat(result.isValid()).isTrue();

	}
}
