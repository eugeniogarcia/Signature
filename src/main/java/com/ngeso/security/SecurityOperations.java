package com.ngeso.security;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Service;

import com.ngeso.security.crypto.KeyManagement;
import com.ngeso.security.crypto.NonRepudiation;
import com.ngeso.security.exception.TokenInvalidException;
import com.ngeso.security.model.Result;
import com.ngeso.security.model.Token;
import com.ngeso.security.model.VerifyResult;
import com.ngeso.security.token.TokenParser;

@Service
public class SecurityOperations implements ISignature{

	private final IPublicKey service;
	private final TokenParser parser=new TokenParser();
	private NonRepudiation signatary;

	private String identityProvider;
	private String audience;

	public SecurityOperations(String identityProvider,String audience,IPublicKey service) throws GeneralSecurityException {
		super();
		this.service = service;
		this.identityProvider=identityProvider;
		this.audience=audience;

		try {
			this.signatary=new NonRepudiation();
		} catch (final NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public SecurityOperations(IPublicKey service) throws GeneralSecurityException {
		this("https://identity.oraclecloud.com/","NGESO",service);
	}

	@Override
	public Result sign(String privateKey, String payload) {
		try {
			return new Result.Builder().isValid(true).output(this.signatary.signString(payload,KeyManagement.parsePrivateCertificate(privateKey))).build();
		} catch (final Exception e) {
			e.printStackTrace();
			return new Result.Builder().isValid(false).errorCode("Exception").message(e.getMessage()).build();
		}
	}

	@Override
	public VerifyResult verify(String jwt, String scope, String payload, String signature) {
		final Token theToken;
		try {
			theToken=parseToken(jwt,scope);
		}catch (final TokenInvalidException ex) {
			ex.printStackTrace();
			return new VerifyResult.Builder().message(ex.getMessage()).errorCode("TokenInvalidException").isValid(false).build();
		}
		// Get Public Key
		final Result res=this.service.getPublicKey(theToken.getMpId());
		if(res.isValid()) {
			//Verify Signature
			try {
				return this.signatary.verifySignatureString(payload, signature, KeyManagement.parsePublicCertificate(res.getOutput()));
			} catch (final Exception e) {
				e.printStackTrace();
				return new VerifyResult.Builder().isValid(false).errorCode("Exception").message(e.getMessage()).build();
			}
		}
		else
		{
			return new VerifyResult.Builder().isValid(false).errorCode(res.getErrorCode()).message(res.getErrorMessage()).build();
		}
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
		if((theToken.getAud()+theToken.getScope()).compareTo(scope)!=0) {
			throw new TokenInvalidException("The Scope is incorrect");
		}
		return theToken;
	}

}
