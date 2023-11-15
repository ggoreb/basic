package com.example.basic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BasicApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(BasicApplication.class, args);

		String raw = "password1234";
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		md.update(raw.getBytes());
		String hex = String.format("%064x", new BigInteger(1, md.digest()));
		System.out.println("raw의 해시값 : " + hex);

	}

}
