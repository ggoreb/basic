package com.example.basic.util;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.stereotype.Component;

@Component
public class EncryptUtil {
  public String encrypt(
      String specName, SecretKey key, IvParameterSpec iv,
      String plainText) throws Exception {
    Cipher cipher = Cipher.getInstance(specName);
    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
    byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
    return new String(Base64.getEncoder().encode(encrypted));
  }

  public SecretKey getKey() throws Exception {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(128);
    SecretKey secretKey = keyGenerator.generateKey();
    return secretKey;
  }

  public IvParameterSpec getIv() {
    byte[] iv = new byte[16];
    new SecureRandom().nextBytes(iv);
    return new IvParameterSpec(iv);
  }


}
