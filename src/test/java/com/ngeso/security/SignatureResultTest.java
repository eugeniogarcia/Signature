package com.ngeso.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.ngeso.security.model.SignatureResult;

public class SignatureResultTest {
	@Test
	public void SignatureResult1() {
		final SignatureResult result=new SignatureResult.Builder()
				.errorCode("myError")
				.isValid(false)
				.message("myCode")
				.build();

		assertThat(result.getErrorCode()).isSameAs("myError");
		assertThat(result.getSignature()).isSameAs("");
		assertThat(result.getMessage()).isSameAs("myCode");
		assertThat(result.isValid()).isFalse();

	}

	public void SignatureResult2() {
		final SignatureResult result=new SignatureResult.Builder()
				.isValid(true)
				.signature("firma")
				.build();

		assertThat(result.getErrorCode()).isSameAs("");
		assertThat(result.getSignature()).isSameAs("firma");
		assertThat(result.getMessage()).isSameAs("");
		assertThat(result.isValid()).isTrue();

	}
}
