package com.sf.utils;

import java.security.Key;

public interface Encryptor {
  byte generateKey();

  byte[] encrypt(byte[] toEncrypt, Key key);

  byte[] decrypt(byte[] toDecrypt, Key key);
}
