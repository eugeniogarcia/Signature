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
			pub = KeyManagement.parsePublicByte(pubBytes);
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
			pub=KeyManagement.parsePublicString(pubString);
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
			generator.writePublic(".\\public.txt");
			pub=KeyManagement.parsePublicFile(".\\public.txt");
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
			priv=KeyManagement.parsePrivateString(privString);
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
			priv = KeyManagement.parsePrivateByte(privBytes);
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
			generator.writePrivate(".\\private.txt");
			priv=KeyManagement.parsePrivateFile(".\\private.txt");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(generator.getPrivateKey()).isEqualTo(priv);

	}

	@Test
	public void CertificateTest() {

		try {
			final PublicKey pubKey=KeyManagement.parsePublicCertificate(Constants.pubKeyCert);
			final PrivateKey privKey=KeyManagement.parsePrivateCertificate(Constants.privateKeyPEM);

			assertThat(pubKey).isNotNull();
			assertThat(privKey).isNotNull();
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
