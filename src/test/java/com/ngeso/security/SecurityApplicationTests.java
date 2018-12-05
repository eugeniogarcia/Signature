package com.ngeso.security;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {

	Signature sign;
	@BeforeClass
	public static void prepare() {
		//sign=new Signature();
	}

	@Test
	public void contextLoads() {
	}
}
