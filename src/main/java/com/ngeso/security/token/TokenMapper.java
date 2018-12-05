package com.ngeso.security.token;
import java.time.format.DateTimeFormatter;

import org.primeframework.jwt.domain.JWT;

import com.ngeso.security.exception.TokenInvalidException;
import com.ngeso.security.model.Token;

public final class TokenMapper {

	private TokenMapper() {
	}

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	static Token createIdentity(final JWT jwt) {

		final String mpId = jwt.getString("client_name");
		if (mpId == null) {
			throw new TokenInvalidException("Market Participant is missing in the token");
		}

		final String iss = jwt.getString("iss");
		if (iss == null) {
			throw new TokenInvalidException("Issuer is missing in the token");
		}

		final String aud = jwt.audience.toString();
		if (aud == null) {
			throw new TokenInvalidException("Audience is missing in the token");
		}

		final String scope = jwt.getString("scope");
		if (scope == null) {
			throw new TokenInvalidException("The token did not have any scope defined");
		}

		final String expiresAt = jwt.expiration.format(DATE_TIME_FORMATTER);
		if (expiresAt == null) {
			throw new TokenInvalidException("Expiration date is not present in the token");
		}

		final String issuedAt = jwt.issuedAt.format(DATE_TIME_FORMATTER);
		final String mpGuid = jwt.getString("client_guid");
		final String clientId = jwt.getString("client_id");

		return new Token.Builder()
				.mpId(mpId)
				.mpGuid(mpGuid)
				.clientId(clientId)
				.aud(aud)
				.iss(iss)
				.scope(scope)
				.expiresAt(expiresAt)
				.issuedAt(issuedAt)
				.build();
	}
}
