package com.ngeso.security;

public class Constants {

	public static final String payload="Hello World";

	//Scopes
	public static final String scopeEDT="NGESOEDT";
	public static final String scopeEDL="NGESOEDL";
	public static final String scopeWA="NGESOEDL_EDT";


	//Oracle IDS
	public final static String url_idc="https://idcs-4a556adae5e44dcfae23a473c9c5e33e.identity.oraclecloud.com";
	//ClientID and Secret for Test_Participant
	public final static String clientID="f86979ecafa94d6495675204ddc14268";
	public final static String  clientSecret="856507ab-ca35-4378-95e1-91534b0b95ab";

	//Public & Private Key associated with the certificate (used to verify the signature or sign)
	//Public key will be retrieved by the Mocked service
	//Private Key will be an input
	public final static String pubKeyCert="-----BEGIN CERTIFICATE-----\r\n" +
			"MIIDsDCCApgCCQC4M2vjkkYL+DANBgkqhkiG9w0BAQsFADCBmTELMAkGA1UEBhMC\r\n" +
			"VUsxEzARBgNVBAgMCldvY2tpbmdoYW0xEzARBgNVBAcMCldvY2tpbmdoYW0xDjAM\r\n" +
			"BgNVBAoMBU5HRVNPMQswCQYDVQQLDAJXQTEfMB0GA1UEAwwWbmdlc28ubmF0aW9u\r\n" +
			"YWxncmlkLmNvbTEiMCAGCSqGSIb3DQEJARYTZWdzbWFydGluQGdtYWlsLmNvbTAe\r\n" +
			"Fw0xODEyMTEwOTUzMjFaFw0xOTAxMTAwOTUzMjFaMIGZMQswCQYDVQQGEwJVSzET\r\n" +
			"MBEGA1UECAwKV29ja2luZ2hhbTETMBEGA1UEBwwKV29ja2luZ2hhbTEOMAwGA1UE\r\n" +
			"CgwFTkdFU08xCzAJBgNVBAsMAldBMR8wHQYDVQQDDBZuZ2Vzby5uYXRpb25hbGdy\r\n" +
			"aWQuY29tMSIwIAYJKoZIhvcNAQkBFhNlZ3NtYXJ0aW5AZ21haWwuY29tMIIBIjAN\r\n" +
			"BgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArG374AEHf8UUPaGneULHtGozm0KT\r\n" +
			"0aO/Hl9n1qV5G+ReGSwgTW3hoFG/l7bIFYAp3KOaxrit1chRqkMPq4MLA3/1LaQx\r\n" +
			"6w1n5tGhLK1RCMd6bn8xhEE4iAEVJt+Iy7xX67mf4rTtcM2zEokx0bTyp6AYLyGF\r\n" +
			"TOUbLmHQlqxBC5S7aaDXLJxH5HUE4Gwi9M43vBBjH8jJ8E84zeks7bZl81OTsFtQ\r\n" +
			"OANJiejjeJrwMosWJZZ8vquax7pU43gTFWa0LowSJi70F1QgeC+G6SbNFf1lYKBy\r\n" +
			"YY5inZTfpYb6txNR3LeFGjib7/tZ7YEwJ0k80pQQTHzT2EX+BG58utrTawIDAQAB\r\n" +
			"MA0GCSqGSIb3DQEBCwUAA4IBAQArwdvB4ba8B0I/I++DhQoZ51OL9HuIZTgnzPS/\r\n" +
			"NZ4Sd8dG7S6b1ljvrxQuDPBIZZ+ERO5HLJF9dlsPAMsieJo5krxs5Nsuadn0fGZZ\r\n" +
			"2+QlIXo1i4hUzwmucChKOz1eOixMRbPgHa0wWVxJbhSdmmenbNL8nl584u74c478\r\n" +
			"jZgZIm59W1XNf+9twLzrs42yfMq1RkZk2UPYy5KuTO4rvSVHZz8G7D6tEPgvm0te\r\n" +
			"C+/LyE0A8Vd4+nHxmgK8bm/mBqOV/65CQ+5O/VaRJ7/HDxA8wbk7FgPtQBN0IGw+\r\n" +
			"Yvn2ZshAjilPUqR/Ds5oLb07vz04c4cVl3z//XJHJVI9BsmB\r\n" +
			"-----END CERTIFICATE-----";

	public final static String privateKeyPEM="-----BEGIN PRIVATE KEY-----\r\n" +
			"MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCsbfvgAQd/xRQ9\r\n" +
			"oad5Qse0ajObQpPRo78eX2fWpXkb5F4ZLCBNbeGgUb+XtsgVgCnco5rGuK3VyFGq\r\n" +
			"Qw+rgwsDf/UtpDHrDWfm0aEsrVEIx3pufzGEQTiIARUm34jLvFfruZ/itO1wzbMS\r\n" +
			"iTHRtPKnoBgvIYVM5RsuYdCWrEELlLtpoNcsnEfkdQTgbCL0zje8EGMfyMnwTzjN\r\n" +
			"6SzttmXzU5OwW1A4A0mJ6ON4mvAyixYllny+q5rHulTjeBMVZrQujBImLvQXVCB4\r\n" +
			"L4bpJs0V/WVgoHJhjmKdlN+lhvq3E1Hct4UaOJvv+1ntgTAnSTzSlBBMfNPYRf4E\r\n" +
			"bny62tNrAgMBAAECggEBAJZ1Og1SmJqS+4Bz6FDVRrFU4kceJyIMwh3cnh0cMrt1\r\n" +
			"3+4TJPSrZu+fpZDau2iXdu/CCrJQP8+Fhk0NS0Ubiaa6JtR2q+AwyHMOhJcZfNYu\r\n" +
			"SdTVQ/3lc1CpsS1EbHdql0Vnqe4GdhGyTAlmkYQtYneGKNQnacuZNsiYLYzsLa3N\r\n" +
			"4bULMwTmpuPZIhRS78NKCG+txOtuWGfY5vSo+0uxKux2HjgCNGiMKyYgNAzBKQi4\r\n" +
			"iBbyr8znDpcFsBM3znqIU3Rveg1t5I1o5cHZlvU3TDfA+GL6TI5V7iaCi7Lm5DVB\r\n" +
			"eK9OA0l2zTjyYblXNNw06JN58jxk9NSrX9+yF0e4XmECgYEA1CTs2FMQgktiEsIc\r\n" +
			"YH1VlAEkJ5Vs4mPhlbwdpZB6Qe4BwJP2ybprJ0CIKjvRXnBW/SAurJQcDZ22HVH2\r\n" +
			"SOSIoD1QgQDV9VxEFG1695nZaiUrDT/I//oM8g9RO18uX7c+gBqlpKQ4Bvj5QU4W\r\n" +
			"pxyAXYmuCbB+dP3NPBcTrhvz0aMCgYEA0BNJPujm+r389PoXVf9GYJdS40PdTnA1\r\n" +
			"9ASJGtkQq4MHBGy/AUNVPJcF8iYfDkx4aEcMFXdXA5RO34uG2YkEeniunSqBREzu\r\n" +
			"bCIzl/2lyMwJU1IU0s0PX4rnVTYRBPOKOu2mrVA8sygFHo9ZkWqBOZFIfGNAacBM\r\n" +
			"64cwf6m145kCgYAFk87yRQTLGCZE7fuHAeSNFpaXv9L0BMI6iI6S+sBsGVDMeldj\r\n" +
			"qFYG2yh5S8dOX0+6Ke1wNhiitwHBtZTvB6sb42DJc3cskzdq/hHcjpvrsXx3RbUI\r\n" +
			"Hc/XUVXGZNM4Sv4Gqiyurm6WqFcNQ3tO+23Yh7Uephg9BSsvKEApNKztVwKBgQCo\r\n" +
			"9qBJ55J5FoIs5FDC0pJxqS/zwI4bPd9yEaTMGmwA+lteangILIcwtmproi0hzVMR\r\n" +
			"SnAzlj8gdvQ9bu3IBA2Q7iLNGVDzB42sXXGMALr4/UatVD/4le/f48nKVicn4CPl\r\n" +
			"ikoI/tjrmpndoQZYoSXie2ljKK2Lsk5EC/mEEWnUMQKBgCQZmhfRzRNNEQgig4Aw\r\n" +
			"yP7S0Z7BY6FKkhSzX5aFc14hqwF1unPLqd2Afs+RZxMIWrXiYMB1ujAdpzbahGnY\r\n" +
			"WSVZV1kn31IWc94ETDrOlvKvuhnoJYCjOvNZMGbmYtr9ffojKhnEvb7i5gscf26x\r\n" +
			"vgLoLLO3XFWHPbn15JwZWFwl\r\n" +
			"-----END PRIVATE KEY-----";
}
