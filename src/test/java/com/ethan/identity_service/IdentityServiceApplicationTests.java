package com.ethan.identity_service;

import jakarta.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
@Slf4j
class IdentityServiceApplicationTests {

	@Test
	void hash() throws NoSuchAlgorithmException {
		String password = "123456";
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		md.update(password.getBytes());
//
//		byte[] digest = md.digest();
//		String md5Hash = DatatypeConverter.printHexBinary(digest);
//
//		log.info("MD5 round 1: {}", md5Hash);
//
//		md.update(password.getBytes());
//		digest = md.digest();
//		md5Hash = DatatypeConverter.printHexBinary(digest);
//
//		log.info("MD5 round 2: {}", md5Hash);

		PasswordEncoder encoder = new BCryptPasswordEncoder(10);
		log.info("BCrypt 1: {}", encoder.encode(password));
		log.info("BCrypt 2: {}", encoder.encode(password));

	}

}
