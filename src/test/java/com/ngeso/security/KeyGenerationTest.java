package com.ngeso.security;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ngeso.security.crypto.KeyManagement;

public class KeyGenerationTest {
	private static KeyManagement generator;

	@BeforeClass
	public static void prepare() {
		try {
			generator=new KeyManagement(512);
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void PubKeyByteTest() {

		generator.createKeys();

		assertThat(generator.getPublicKey()).isNotNull();
		final byte[] pubBytes= generator.getPublicByte();
		PublicKey pub=null;
		try {
			pub = generator.setPublicByte(pubBytes);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(generator.getPublicKey()).isEqualTo(pub);
	}

	@Test
	public void PubKeyStringTest() {

		generator.createKeys();

		assertThat(generator.getPublicKey()).isNotNull();

		PublicKey pub=null;
		final String pubString=generator.getPublicString();
		try {
			pub=generator.setPublicString(pubString);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(generator.getPublicKey()).isEqualTo(pub);

	}

	@Test
	public void PubKeyFileTest() {

		generator.createKeys();

		assertThat(generator.getPublicKey()).isNotNull();

		PublicKey pub=null;
		try {
			generator.setPublicFile(".\\public.txt");
			pub=generator.getPublicFile(".\\public.txt");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(generator.getPublicKey()).isEqualTo(pub);

	}

	@Test
	public void PrivKeyStringTest() {

		generator.createKeys();

		PrivateKey priv=null;
		final String privString=generator.getPrivateString();
		try {
			priv=generator.setPrivateString(privString);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(generator.getPrivateKey()).isEqualTo(priv);

	}

	@Test
	public void PrivKeyByteTest() {

		generator.createKeys();

		assertThat(generator.getPrivateKey()).isNotNull();
		final byte[] privBytes= generator.getPrivateByte();
		PrivateKey priv=null;
		try {
			priv = generator.setPrivateByte(privBytes);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(generator.getPrivateKey()).isEqualTo(priv);

	}

	@Test
	public void PrivKeyFileTest() {

		generator.createKeys();

		assertThat(generator.getPrivateKey()).isNotNull();

		PrivateKey priv=null;
		try {
			generator.setPrivateFile(".\\private.txt");
			priv=generator.getPrivateFile(".\\private.txt");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(generator.getPrivateKey()).isEqualTo(priv);

	}

}
