# Security
Sample project that provides utilities to encrypt & decrypt, sign & verify and manage key pairs.

It uses these sample files to implement a Bean that verifies and signs a payload.

There are automated UT that can be used to understand how the utilities work.

## KeyManagement

### Constructors
public KeyManagement(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException
Constructor that iniotializes the Key Generator. It takes as an argument the key length

public void createKeys()
Method that generates a fresh Key pair

### Getters
#### public PrivateKey getPrivateKey()
Returns the Private Key (object)

#### public PublicKey getPublicKey()
Returns the Public Key (object)

#### public String getPrivateString()
Returns the Private Key encoded as an string

#### public String getPublicString()
Returns the Public Key encoded as an string

#### public byte[] getPrivateByte()
Returns the Private Key encoded as a byte array

#### public byte[] getPublicByte() 
Returns the Public Key encoded as a byte array

### Parsers
The parsers are static methods that do not update or use the Key pair stored on the class. It is used to parse the Key into different formats/encodings

#### public static PrivateKey parsePrivateString(String privKey) throws Exception
Takes the Private Key as a string and returns it as an Object

#### public static PublicKey parsePublicString(String pubKey) throws Exception
Takes the Public Key as a string and returns it as an Object

#### public static PrivateKey parsePrivateFile(String filename) throws Exception
Takes the Private Key stored on a file (64 encoded) and returns it as an Object

#### public static PublicKey parsePublicFile(String filename) throws Exception
Takes the Public Key stored on a file (64 encoded) and returns it as an Object

#### public static PrivateKey parsePrivateByte(byte[] privKey) throws Exception
Takes the Private Key as a byte array and returns it as an Object

#### public static PublicKey parsePublicByte(byte[] pubKey) throws Exception
Takes the Public Key as a byte array and returns it as an Object

#### public static PrivateKey parsePrivateCertificate(String privateKeyPEM) throws Exception
Takes the Private Key as a PEM certificate and returns it as an Object

#### public static PublicKey parsePublicCertificate(String publicCertificate) throws Exception
Takes the Public Key as a PEM certificate and returns it as an Object

### Files Management
These methods allow us to write the Private and Public Keys into files

#### public void writePrivate(String filename) throws Exception
Writes the Private Key on a File

#### public void writePublic(String filename) throws Exception
Writes the Public Key on a File

#### public void writeToFile(String path, byte[] key) throws IOException
This method is not really related to Private or Public Keys. It is used to write an array of bytes into a file

## Encrypt
Utilities to encrypt and decrypt using private and public keys

### public void encryptFile(String filename, byte[] input, PrivateKey key)
Takes an input stream in bytes, and using the PrivateKey encrypts it, and writes the results on a file

### public void decryptFile(String filename, byte[] input, PublicKey key)
Takes an input stream in bytes, and using the PublicKey decrypts it, and writes the results on a file

### public String encryptText(String msg, PrivateKey key)
Takes an string and encrypts it using the Private Key

### public String decryptText(String msg, PublicKey key)
Takes an string and decrypts it using the Public Key

### public byte[] getFileInBytes(String filename)
Utility that takes a file as input, and returns its content in bytes

## NonRepudiation

### public VerifyResult writeSignedPayload(String filename,String data, PrivateKey RSAPrivateKey)
Writes on a file the input data plus a signature that is created with the Private key

### public VerifyResult readAndVerifySignedPayload(String filename, PublicKey RSAPublicKey) throws Exception
Takes a file that contains a paylod and signature, and verifies that the signature is correct using the Public key

### public VerifyResult verifySignatureString(String data, String signature, PublicKey RSAPublicKey)
Takes an string and a signature, and verifies that the signature is correct using the Public Key

### public VerifyResult verifySignatureByte(String data, byte[] signature, PublicKey RSAPublicKey)
Takes an array of bytes and a signature, and verifies that the signature is correct using the Public Key

### public String signString(String data, PrivateKey RSAPrivateKey) throws InvalidKeyException, Exception
Takes an string and signs it using the Private Key provided

### public byte[] signByte(String data, PrivateKey RSAPrivateKey) throws InvalidKeyException, Exception
Takes an array of bytes and signs them using the Private Key provided



