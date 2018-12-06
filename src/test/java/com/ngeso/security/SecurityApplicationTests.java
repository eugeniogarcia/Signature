package com.ngeso.security;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {

	SecurityOperations sign;
	@BeforeClass
	public static void prepare() {
		//sign=new Signature();
		//Probar que lea del archivo de propiedades

	}

	@Test
	public void contextLoads() {
	}
}
