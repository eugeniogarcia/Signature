package com.ngeso.security;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

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

		final String from="Data Source";
		String to="";
		try {
			to=cifra.encryptText(from,generator.getPrivateKey());
			to=cifra.decryptText(to,generator.getPublicKey());

		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException
				| IllegalBlockSizeException | BadPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		assertThat(to).isEqualTo(from);
	}

	@Test
	public void EncryptFileTest() {

		final String from="Data Source";
		String to="";
		try {
			cifra.encryptFile(".\\Encrypted.txt",from.getBytes(),generator.getPrivateKey());
			cifra.decryptFile(".\\Decrypted.txt",cifra.getFileInBytes(".\\Encrypted.txt") , generator.getPublicKey());
			to=new String(cifra.getFileInBytes(".\\Decrypted.txt"));
		} catch (IOException | GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThat(to).isEqualTo(from);
	}
}
