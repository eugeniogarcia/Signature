package com.ngeso.security.crypto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

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

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public void setInFilePrivate(String filename) throws Exception {
		writeToFile(filename,this.privateKey.getEncoded());
	}

	public PrivateKey getPrivate(String filename) throws Exception {
		final byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		final KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

	public PublicKey getPublic(String filename) throws Exception {
		final byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		final X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		final KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}

	public void setInFilePublic(String filename) throws Exception {
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
