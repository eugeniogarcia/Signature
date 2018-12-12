package com.ngeso.security;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

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

		String theSignature;
		VerifyResult result;
		try {
			theSignature=signature.signString(Constants.payload,generator.getPrivateKey());
			result=signature.verifySignatureString(Constants.payload,theSignature,generator.getPublicKey());
			assertThat(result.isValid()).isTrue();
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void SignByteTest() {

		byte[] theSignature;
		VerifyResult result;
		try {
			theSignature=signature.signByte(Constants.payload,generator.getPrivateKey());
			result=signature.verifySignatureByte(Constants.payload,theSignature,generator.getPublicKey());
			assertThat(result.isValid()).isTrue();
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void SignFileTest() {

		try {
			VerifyResult result= signature.writeSignedPayload(".\\Signed.txt",Constants.payload,generator.getPrivateKey());
			assertThat(result.isValid()).isTrue();
			result=signature.readAndVerifySignedPayload(".\\Signed.txt", generator.getPublicKey());
			assertThat(result.isValid()).isTrue();
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void SignCertTest() {

		VerifyResult result=new VerifyResult.Builder().isValid(false).build();

		try {
			final PublicKey pubKey=KeyManagement.parsePublicCertificate(Constants.pubKeyCert);
			final PrivateKey privKey=KeyManagement.parsePrivateCertificate(Constants.privateKeyPEM);

			assertThat(pubKey).isNotNull();
			assertThat(privKey).isNotNull();

			String theSignature;
			theSignature=signature.signString(Constants.payload,privKey);
			result=signature.verifySignatureString(Constants.payload,theSignature,pubKey);

		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThat(result.isValid()).isTrue();

	}

}
