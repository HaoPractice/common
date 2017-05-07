package com.sf.utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.validation.constraints.NotNull;

public enum Encrypts implements Encryptor{
  DES;

  private static final String DES_STRING = "DES";
  // private static final String DES3 = "DESede";// TODO 增加对其他的加密方式的支持
  private static final String DEFAULT_SALT = "DefaultSalt";

  public static String des(String original) {
    return original;
  }

  private static Cipher getCipher(String algorithm, Key key, int mode) {
    try {
      Cipher cipher = Cipher.getInstance(algorithm);
      cipher.init(mode, key);
      return cipher;
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private static Cipher getEncryptCipher(String algorithm, Key key) {
    return getCipher(algorithm, key, Cipher.ENCRYPT_MODE);
  }

  private static Cipher getDecryptCipher(String algorithm, Key key) {
    return getCipher(algorithm, key, Cipher.DECRYPT_MODE);
  }

  public static byte[] encryptDes(byte[] original, Key key) {
    Cipher encryptCipher = getEncryptCipher(DES_STRING, key);
    try {
      byte[] doFinal = encryptCipher.doFinal(original);
      return doFinal;
    } catch (IllegalBlockSizeException | BadPaddingException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static byte[] decryptDes(@NotNull byte[] encrypted, Key key) {
    Cipher encryptCipher = getDecryptCipher(DES_STRING, key);
    try {
      byte[] doFinal = encryptCipher.doFinal(encrypted);
      return doFinal;
    } catch (IllegalBlockSizeException | BadPaddingException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private static Key getKey(String algorithm, int padding) {
    try {
      KeyGenerator generator = KeyGenerator.getInstance(algorithm);
      generator.init(padding);
      SecretKey generateKey = generator.generateKey();
      return generateKey;
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static Key getDesKey() {
    return getKey(DES_STRING, 56);
  }

  public static String md5String(String original) {
    return md5String(original, DEFAULT_SALT);
  }

  public static String md5String(String original, String salt) {
    byte[] md5 = md5((original + salt).getBytes());
    return Base64.getEncoder().encodeToString(md5);
  }

  private static byte[] md5(byte[] bytes) {
    try {
      return MessageDigest.getInstance("MD5").digest(bytes);
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public byte generateKey() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public byte[] encrypt(byte[] toEncrypt, Key key) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public byte[] decrypt(byte[] toDecrypt, Key key) {
    // TODO Auto-generated method stub
    return null;
  }
}
