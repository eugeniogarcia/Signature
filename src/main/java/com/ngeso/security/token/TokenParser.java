package com.ngeso.security.token;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.primeframework.jwt.Verifier;
import org.primeframework.jwt.domain.JWT;
import org.primeframework.jwt.domain.JWTException;
import org.primeframework.jwt.domain.JWTExpiredException;
import org.primeframework.jwt.rsa.RSAVerifier;

import com.ngeso.security.exception.SystemException;
import com.ngeso.security.exception.TokenExpiredException;
import com.ngeso.security.exception.TokenInvalidException;
import com.ngeso.security.exception.UnknownEnvironmentException;
import com.ngeso.security.model.Token;

public class TokenParser {
	private static final List<String> ENVIRONMENTS = Arrays.asList("dev", "test", "sandbox","fof", "prepro", "prod");

	protected List<Verifier> verifiers;

	public TokenParser() {
		try {
			verifiers = new ArrayList<>();
			verifiers.add(createVerifier());

		} catch (final JWTException exc) {
			throw new SystemException(exc);
		}
	}

	private RSAVerifier createVerifier() {
		final String filename = "PubCert.pem";
		final String publicKey = getResourceFileAsString(getClass(), filename);
		return RSAVerifier.newVerifier(publicKey);
	}

	private static String getResourceFileAsString(final Class<?> clazz, final String resourceFileName) {
		final InputStream is = clazz.getClassLoader().getResourceAsStream(resourceFileName);
		if (is == null) {
			throw new UnknownEnvironmentException(String.format("Resource %s not found", resourceFileName));
		}
		final BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		return reader.lines().collect(Collectors.joining(System.lineSeparator()));
	}

	private JWT verifyToken(final String token) {
		for (final Verifier verifier : verifiers) {
			try {
				return JWT.getDecoder().decode(token, verifier);
			} catch (final JWTExpiredException exc) {
				throw new TokenExpiredException(exc);
			} catch (final JWTException exc) {
				// ignore and try the next key
			}
		}
		throw new TokenInvalidException("Token invalid");
	}

	public Token verifyTokenAndExtractIdentity(final String token) {
		final JWT jwt = verifyToken(token);
		return TokenMapper.createIdentity(jwt);
	}
}

