package com.ngeso.security.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

import com.ngeso.security.model.VerifyResult;

public class NonRepudiation {

	private final Signature rsa;

	public NonRepudiation() throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.rsa = Signature.getInstance("SHA1withRSA");
	}

	public byte[] sign(String data, PrivateKey RSAPrivateKey) throws InvalidKeyException, Exception{
		rsa.initSign(RSAPrivateKey, new SecureRandom());
		final byte[] message = data.getBytes();
		rsa.update(message);
		return rsa.sign();
	}

	public VerifyResult writeSignedPayload(String data, PrivateKey RSAPrivateKey,String filename) {
		final List<byte[]> list= new ArrayList<byte[]>();

		try {
			list.add(data.getBytes());
			list.add(sign(data, RSAPrivateKey));

			final File f = new File(filename);
			f.getParentFile().mkdirs();
			final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(list);
			out.flush();
			out.close();

			return new VerifyResult.Builder().isValid(true).build();
		}
		catch(final InvalidKeyException ex) {
			return new VerifyResult.Builder().errorCode("InvalidKeyException").message("Wrong Key. "+ex.getMessage()).isValid(false).build();
		}
		catch(final IOException ex) {
			return new VerifyResult.Builder().errorCode("IOException").message("Error while writing the signed payload. "+ex.getMessage()).isValid(false).build();
		}
		catch(final Exception ex) {
			return new VerifyResult.Builder().errorCode("Exception").message(ex.getMessage()).isValid(false).build();
		}
	}

	public VerifyResult verifySignature(byte[] data, byte[] signature, PublicKey RSAPublicKey) {
		try {
			rsa.initVerify(RSAPublicKey);
			rsa.update(data);
			return new VerifyResult.Builder().isValid(rsa.verify(signature)).build();
		}
		catch (final SignatureException ex) {
			return new VerifyResult.Builder().errorCode("SignatureException").message("Verify Signature Error. "+ex.getMessage()).isValid(false).build();
		}
		catch (final InvalidKeyException ex) {
			return new VerifyResult.Builder().errorCode("InvalidKeyException").message("Verify Signature Error. "+ex.getMessage()).isValid(false).build();
		}
		catch(final Exception ex) {
			return new VerifyResult.Builder().errorCode("Exception").message(ex.getMessage()).isValid(false).build();
		}
	}

	@SuppressWarnings("unchecked")
	public VerifyResult readAndVerifySignedPayload(String filename, PublicKey RSAPublicKey) throws Exception {
		try {
			List<byte[]> list= new ArrayList<byte[]>();
			final ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			list = (List<byte[]>) in.readObject();
			in.close();
			return verifySignature(list.get(0), list.get(1), RSAPublicKey);
		}
		catch(final IOException ex) {
			return new VerifyResult.Builder().errorCode("IOException").message("Error while verifying the written payload. "+ex.getMessage()).isValid(false).build();
		}
		catch(final Exception ex) {
			return new VerifyResult.Builder().errorCode("Exception").message(ex.getMessage()).isValid(false).build();
		}
	}

}
