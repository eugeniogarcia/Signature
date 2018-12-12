package com.ngeso.security;

import com.ngeso.security.model.Result;

public interface IPublicKey {
	//Gets the Public Key of a MP
	Result getPublicKey(String mpID);
}
