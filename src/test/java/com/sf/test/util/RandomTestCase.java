package com.sf.test.util;

import java.util.Random;

import org.junit.Test;

public class RandomTestCase {
  
  // 在种子相同的情况下，得到的 随机数相同
  @Test
  public void testRandomInt() throws InterruptedException{
    Random  r = new Random(1);
    
    System.out.println(r.nextInt());
    System.out.println(r.nextInt());
    Random  r2 = new Random(1);
    System.out.println(r2.nextInt());
    System.out.println(r2.nextInt());
    
  }
  
}
