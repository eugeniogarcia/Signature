package com.ngeso.security;

import com.ngeso.security.model.Result;
import com.ngeso.security.model.VerifyResult;

public interface ISignature {
	//Verifies if a Payload is properly signed. The token and scope should be valid
	VerifyResult verify(String jwt,String scope,String payload, String signature);
	//Signs a payload using a private key
	Result sign(String privateKey,String payload);
}
