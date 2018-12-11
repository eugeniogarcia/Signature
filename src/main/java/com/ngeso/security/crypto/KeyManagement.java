package com.ngeso.security.crypto;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyManagement {
	private final KeyPairGenerator keyGen;

	private KeyPair pair;
	private PrivateKey privateKey;
	private PublicKey publicKey;

	public KeyManagement(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
		this.keyGen = KeyPairGenerator.getInstance("RSA");
		this.keyGen.initialize(keylength, new SecureRandom());
	}

	public void createKeys() {
		this.pair = this.keyGen.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
	}


	//Getters
	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

	public String getPrivateString() {
		return Base64.getEncoder().encodeToString(this.privateKey.getEncoded());
	}

	public String getPublicString() {
		return Base64.getEncoder().encodeToString(this.publicKey.getEncoded());
	}

	public byte[] getPrivateByte(){
		return this.privateKey.getEncoded();
	}

	public byte[] getPublicByte() {
		return this.publicKey.getEncoded();
	}

	//Parsers
	public static PrivateKey parsePrivateString(String privKey) throws Exception {
		final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privKey));

		final KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	public static PublicKey parsePublicString(String pubKey) throws Exception {
		final X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(pubKey));
		final KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}

	public static PrivateKey parsePrivateFile(String filename) throws Exception {
		final byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		final KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	public static PublicKey parsePublicFile(String filename) throws Exception {
		final byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		final X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		final KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}

	public static PrivateKey parsePrivateByte(byte[] privKey) throws Exception {
		final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privKey);
		final KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	public static PublicKey parsePublicByte(byte[] pubKey) throws Exception {
		final X509EncodedKeySpec spec = new X509EncodedKeySpec(pubKey);
		final KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}


	public static PrivateKey parsePrivateCertificate(String privateKeyPEM) throws Exception {
		// strip of header, footer, newlines, whitespaces
		privateKeyPEM = privateKeyPEM
				.replace("-----BEGIN PRIVATE KEY-----", "")
				.replace("-----END PRIVATE KEY-----", "")
				.replaceAll("\\s", "");

		// decode to get the binary DER representation
		final byte[] privateKeyDER = Base64.getDecoder().decode(privateKeyPEM);

		final KeyFactory keyFactory=KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyDER));
	}

	public static PublicKey parsePublicCertificate(String publicCertificate) throws Exception {
		final InputStream fin = new ByteArrayInputStream(publicCertificate.getBytes());
		final CertificateFactory f=CertificateFactory.getInstance("X.509");
		final X509Certificate certificate= (X509Certificate)f.generateCertificate(fin);
		return certificate.getPublicKey();
	}

	//Write Files
	public void writePrivate(String filename) throws Exception {
		writeToFile(filename,this.privateKey.getEncoded());
	}

	public void writePublic(String filename) throws Exception {
		writeToFile(filename,this.publicKey.getEncoded());
	}

	public void writeToFile(String path, byte[] key) throws IOException {
		final File f = new File(path);
		f.getParentFile().mkdirs();

		final FileOutputStream fos = new FileOutputStream(f);
		fos.write(key);
		fos.flush();
		fos.close();
	}

}
