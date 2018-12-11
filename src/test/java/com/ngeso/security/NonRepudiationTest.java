package com.ngeso.security;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.NoSuchPaddingException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ngeso.security.crypto.KeyManagement;
import com.ngeso.security.crypto.NonRepudiation;
import com.ngeso.security.model.VerifyResult;

public class NonRepudiationTest {
	private static NonRepudiation signature;
	private static KeyManagement generator;

	@BeforeClass
	public static void prepare() {
		try {
			signature=new NonRepudiation();
			generator=new KeyManagement(512);
			generator.createKeys();
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void SignStringTest() {

		final String payload="Data Source";
		String theSignature;
		VerifyResult result;
		try {
			theSignature=signature.signString(payload,generator.getPrivateKey());
			result=signature.verifySignatureString(payload,theSignature,generator.getPublicKey());
			assertThat(result.isValid()).isTrue();
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void SignByteTest() {

		final String payload="Data Source";
		byte[] theSignature;
		VerifyResult result;
		try {
			theSignature=signature.signByte(payload,generator.getPrivateKey());
			result=signature.verifySignatureByte(payload,theSignature,generator.getPublicKey());
			assertThat(result.isValid()).isTrue();
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void SignFileTest() {

		final String from="Data Source";
		try {
			VerifyResult result= signature.writeSignedPayload(".\\Signed.txt",from,generator.getPrivateKey());
			assertThat(result.isValid()).isTrue();
			result=signature.readAndVerifySignedPayload(".\\Signed.txt", generator.getPublicKey());
			assertThat(result.isValid()).isTrue();
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
