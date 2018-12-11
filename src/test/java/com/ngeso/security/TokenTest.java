package com.ngeso.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.ngeso.security.model.Token;


public class TokenTest {
	@Test
	public void TokenCreation() {
		final Token myToken=new Token.Builder()
				.aud("myaud")
				.clientId("myclientid")
				.expiresAt("2018-12-05 12:51:48")
				.iss("myiss")
				.issuedAt("2018-12-05 12:56:48")
				.mpGuid("myMpGuid")
				.mpId("myMpId")
				.scope("myScope")
				.build();

		assertThat(myToken.getAud()).isSameAs("myaud");
		assertThat(myToken.getClientId()).isSameAs("myclientid");
		assertThat(myToken.getIss()).isSameAs("myiss");
		assertThat(myToken.getMpGuid()).isSameAs("myMpGuid");
		assertThat(myToken.getMpId()).isSameAs("myMpId");
		assertThat(myToken.getScope()).isSameAs("myScope");
	}

}
