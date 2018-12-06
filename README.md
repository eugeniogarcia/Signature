"# Signature" 


	public static void main(String[] args) throws Exception {
		final Encrypt ac = new Encrypt();
		final PrivateKey privateKey = ac.getPrivate("KeyPair/privateKey");
		final PublicKey publicKey = ac.getPublic("KeyPair/publicKey");

		final String msg = "Cryptography is fun!";
		final String encrypted_msg = ac.encryptText(msg, privateKey);
		final String decrypted_msg = ac.decryptText(encrypted_msg, publicKey);
		System.out.println("Original Message: " + msg +
				"\nEncrypted Message: " + encrypted_msg
				+ "\nDecrypted Message: " + decrypted_msg);

		if (new File("KeyPair/text.txt").exists()) {
			ac.encryptFile(ac.getFileInBytes(new File("KeyPair/text.txt")),
					new File("KeyPair/text_encrypted.txt"),privateKey);
			ac.decryptFile(ac.getFileInBytes(new File("KeyPair/text_encrypted.txt")),
					new File("KeyPair/text_decrypted.txt"), publicKey);
		} else {
			System.out.println("Create a file text.txt under folder KeyPair");
		}
	}