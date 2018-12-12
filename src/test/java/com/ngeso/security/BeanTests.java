package com.ngeso.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.GeneralSecurityException;
import java.security.PrivateKey;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ngeso.security.crypto.KeyManagement;
import com.ngeso.security.crypto.NonRepudiation;
import com.ngeso.security.model.Result;
import com.ngeso.security.model.VerifyResult;
import com.ngeso.security.model.JWT;


public class BeanTests {

	private static SecurityOperations sign;
	private static String signature_OK,	jwt_TestParticipant_EDT;
	private static NonRepudiation signature;

	//Wrong signature
	private static final String signature_KO="blablabla";


	@BeforeClass
	public static void prepare() throws GeneralSecurityException {

		final IPublicKey service= mock(IPublicKey.class);
		final Result ok=new Result.Builder().isValid(true).output(Constants.pubKeyCert).build();
		when(service.getPublicKey("Test_Participant")).thenReturn(ok);
		final Result ko=new Result.Builder().isValid(false).output("").errorCode("Not Found").message("Not Found").build();
		when(service.getPublicKey("Participant")).thenReturn(ko);

		//Smaple Payload
		signature_OK="";

		PrivateKey privKey;
		try {
			privKey = KeyManagement.parsePrivateCertificate(Constants.privateKeyPEM);
			signature=new NonRepudiation();
			signature_OK=signature.signString(Constants.payload,privKey);
		} catch (final Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		jwt_TestParticipant_EDT=getToken(Constants.scopeEDT);

		try {
			sign=new SecurityOperations(service);
		} catch (final GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public void verifySignatureOkTest() {
		final VerifyResult res=sign.verify(jwt_TestParticipant_EDT, Constants.scopeEDT, Constants.payload, signature_OK);
		assertThat(res.isValid()).isTrue();
		assertThat(res.getErrorCode()).isEmpty();
		assertThat(res.getMessage()).isEmpty();
	}

	@Test
	public void verifySignatureWrongSignatureTest() {
		final VerifyResult res=sign.verify(jwt_TestParticipant_EDT, Constants.scopeEDT, Constants.payload, signature_KO);
		assertThat(res.isValid()).isFalse();
		assertThat(res.getErrorCode()).isNotEmpty();
		assertThat(res.getMessage()).isNotEmpty();
	}

	@Test
	public void verifySignatureInWrongScopeTest() {
		final VerifyResult res=sign.verify(jwt_TestParticipant_EDT, Constants.scopeEDL, Constants.payload, signature_OK);
		assertThat(res.isValid()).isFalse();
		assertThat(res.getErrorCode()).isNotEmpty();
		assertThat(res.getMessage()).isNotEmpty();
	}

	@Test
	public void signPayloadOKTest() {
		final Result res=sign.sign(Constants.privateKeyPEM, Constants.payload);
		assertThat(res.isValid()).isTrue();
		assertThat(res.getOutput()).isNotEmpty();
		assertThat(res.getErrorCode()).isEmpty();
		assertThat(res.getErrorMessage()).isEmpty();

		final VerifyResult respose=sign.verify(jwt_TestParticipant_EDT, Constants.scopeEDT, Constants.payload, res.getOutput());
		assertThat(respose.isValid()).isTrue();
		assertThat(respose.getErrorCode()).isEmpty();
		assertThat(respose.getMessage()).isEmpty();

	}

	@Test
	public void signPayloadWrongPrivKeyTest() {
		final Result res=sign.sign(Constants.privateKeyPEM+"x", Constants.payload);
		assertThat(res.isValid()).isFalse();
		assertThat(res.getOutput()).isEmpty();
		assertThat(res.getErrorCode()).isNotEmpty();
		assertThat(res.getErrorMessage()).isNotEmpty();
	}

	private static RestTemplate rt=new RestTemplate();

	public static String getToken(String scope) {

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setBasicAuth(Constants.clientID, Constants.clientSecret);

		final MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("grant_type", "client_credentials");
		map.add("scope", scope);
		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(Constants.url_idc+"/oauth2/v1/token");

		final ResponseEntity<JWT> resp=rt.postForEntity(builder.build().toUri(),request,JWT.class);
		return resp.getBody().getAccess_token();
	}

}
