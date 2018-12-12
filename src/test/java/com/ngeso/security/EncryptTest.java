package com.ngeso.security;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ngeso.security.crypto.Encrypt;
import com.ngeso.security.crypto.KeyManagement;

public class EncryptTest {
	private static Encrypt cifra;
	private static KeyManagement generator;

	@BeforeClass
	public static void prepare() {
		try {
			cifra=new Encrypt();
			generator=new KeyManagement(512);
			generator.createKeys();
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void EncryptStringTest() {

		String to="";
		try {
			to=cifra.encryptText(Constants.payload,generator.getPrivateKey());
			to=cifra.decryptText(to,generator.getPublicKey());

		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException
				| IllegalBlockSizeException | BadPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		assertThat(to).isEqualTo(Constants.payload);
	}

	@Test
	public void EncryptFileTest() {

		String to="";
		try {
			cifra.encryptFile(".\\Encrypted.txt",Constants.payload.getBytes(),generator.getPrivateKey());
			cifra.decryptFile(".\\Decrypted.txt",cifra.getFileInBytes(".\\Encrypted.txt") , generator.getPublicKey());
			to=new String(cifra.getFileInBytes(".\\Decrypted.txt"));
		} catch (IOException | GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThat(to).isEqualTo(Constants.payload);
	}


	@Test
	public void EncryptCertTest() {

		String to="";

		try {
			final PublicKey pubKey=KeyManagement.parsePublicCertificate(Constants.pubKeyCert);
			final PrivateKey privKey=KeyManagement.parsePrivateCertificate(Constants.privateKeyPEM);

			assertThat(pubKey).isNotNull();
			assertThat(privKey).isNotNull();

			try {
				to=cifra.encryptText(Constants.payload,privKey);
				to=cifra.decryptText(to,pubKey);

			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException
					| IllegalBlockSizeException | BadPaddingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertThat(to).isEqualTo(Constants.payload);

	}

}
