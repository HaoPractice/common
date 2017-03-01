package com.sf.test.util;

import org.junit.Test;

import com.sf.utils.AccumlativeStopWatch;

public class StopWatchTestCase {
  @Test
  public void testSW() throws InterruptedException{
    AccumlativeStopWatch sw = new AccumlativeStopWatch("testSW");
    
    sw.start();
    Thread.sleep(10);
    sw.stop();
    
    for (int i = 0; i < 400000; i++) {
      sw.start("tasssssssk");
      sw.stop();
    }
    for (int i = 0; i < 8; i++) {
      sw.start("1");
      Thread.sleep(10);
      sw.stop();
    }
    for (int i = 0; i < 20; i++) {
      sw.start("2");
      Thread.sleep(10);
      sw.stop();
    }
    System.out.println(sw.prettyPrint());
    
  }
  @Test
  public void testSWWithName() throws InterruptedException{
    AccumlativeStopWatch sw = new AccumlativeStopWatch("aaaaaaaaaaaaaaaaaaaaaaa");
    sw.start();
    Thread.sleep(10);
    sw.stop();
    System.out.println(sw.prettyPrint());
  }
  @Test
  public void testSWNoName() throws InterruptedException{
    AccumlativeStopWatch sw = new AccumlativeStopWatch();
    sw.start();
    Thread.sleep(10);
    sw.stop();
    System.out.println(sw.prettyPrint());
    
  }
}
