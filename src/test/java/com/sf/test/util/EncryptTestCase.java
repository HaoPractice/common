package com.sf.test.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import com.sf.utils.Encrypts;

public class EncryptTestCase {
  @Test
  public void testSW() throws InterruptedException {

    String original = ("aaaa--***Encrypts");


    try {
      // 生成key
      KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
      keyGenerator.init(56);
      SecretKey generateKey = keyGenerator.generateKey();
      byte[] encoded = generateKey.getEncoded();
      KeySpec keySpec = new DESKeySpec(encoded);
      Key key = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
      System.out.println(key);
      System.out.println(generateKey);
      // 获取加密器
      Cipher encryptor = Cipher.getInstance("DES");
      // 设置加密模式，加密key
      encryptor.init(Cipher.ENCRYPT_MODE, generateKey);

      // 加密信息
      byte[] input = original.getBytes();
      byte[] ciphered = encryptor.doFinal(input);
      System.out.println(Base64Utils.encodeToString(ciphered));

      // 获取解密器
      Cipher deCryptor = Cipher.getInstance("DES");
      // 设置加密模式，加密key
      deCryptor.init(Cipher.DECRYPT_MODE, key);
      byte[] deCiphered = deCryptor.doFinal(ciphered);
      System.out.println(new String(deCiphered));

    } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException
        | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
      e.printStackTrace();
    }

  }

  @Test
  public void testMd5() {
    String md5String = Encrypts.md5String("aaasdfdsgt222");
    String md5String2 = Encrypts.md5String("aaasdfdsgt");
    String md5String3 = Encrypts.md5String("aaasdfdsgt");
    
    System.out.println(md5String);
    System.out.println(md5String2);
    System.out.println(md5String3);
    
    Assert.assertFalse(md5String.equals(md5String2));
    Assert.assertTrue(md5String2.equals(md5String3));
  }
  @Test
  public void testCipher() throws UnsupportedEncodingException {
    Key desKey = Encrypts.getDesKey();
    Key desKey2 = Encrypts.getDesKey();
    Assert.assertTrue(desKey != desKey2);

    byte[] toEncrypt = "czaaa我发誓的个人推荐韩国艺人".getBytes();

    byte[] encrypted1 = Encrypts.encryptDes(toEncrypt, desKey);
    byte[] encrypted2 = Encrypts.encryptDes(toEncrypt, desKey2);


    byte[] decryptDes = Encrypts.decryptDes(encrypted1, desKey);
    System.out.println(String.format("####%s", new String(decryptDes)));
    byte[] decryptDes_2 = Encrypts.decryptDes(encrypted2, desKey2);

    System.out.println(String.format("####%s", new String(decryptDes_2)));

  }
}
