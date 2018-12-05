package com.ngeso.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ngeso.security.exception.TokenInvalidException;
import com.ngeso.security.model.Token;
import com.ngeso.security.model.VerifyResult;
import com.ngeso.security.token.TokenParser;

@Service
public class Signature implements ISignature{

	private final IPublicKey service;
	private final TokenParser parser=new TokenParser();

	@Value("${IDP:'https://identity.oraclecloud.com/'}")
	private String identityProvider;
	@Value("${AUD:'NGESO'}")
	private String audience;

	public Signature(IPublicKey service) {
		super();
		this.service = service;
	}

	@Override
	public String sign(String privateKey, String payload) {
		//Sign payload
		//AQUI
		//return payload
		return "";
	}

	@Override
	public VerifyResult verify(String jwt, String scope, String payload, String signature) {
		final Token theToken;
		try {
			//Verify Token
			theToken=parseToken(jwt,scope);
		}catch (final TokenInvalidException ex) {
			return new VerifyResult.Builder().message(ex.getMessage()).errorCode("TokenInvalidException").isValid(false).build();
		}

		// Get Public Key
		final String pubKey=this.service.getPublicKey(theToken.getMpId());

		//Verify Signature
		//AQUI

		return new VerifyResult.Builder().isValid(true).build();
	}


	//Parses a Token. Verifies that the token is valid for a given scope
	private Token parseToken(String jwt,String scope) throws TokenInvalidException{
		//Verify Token Signature
		final Token theToken=parser.verifyTokenAndExtractIdentity(jwt);
		//Check that the IDP is the expected one
		if(theToken.getIss().compareTo(identityProvider)!=0) {
			throw new TokenInvalidException("The Identy Provider is incorrect");
		}
		if(theToken.getAud().compareTo(audience)!=0) {
			throw new TokenInvalidException("The Audience is incorrect");
		}
		//Verify Token scope
		if(theToken.getScope().compareTo(scope)!=0) {
			throw new TokenInvalidException("The Scope is incorrect");
		}
		return theToken;
	}


}