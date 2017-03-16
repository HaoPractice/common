package com.sf.test.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class LoggerTestCase {
  Logger logger = LogManager.getLogger(getClass());

  @Test
  public void testLogger() {

    logger.trace("{},{},{}", "aaa", "bbb", "cc");
    logger.debug("{},{},{}", "aaa", "bbb", "cc");
    logger.info("{},{},{}", "aaa", "bbb", "cc");
    logger.warn("{},{},{}", "aaa", "bbb", "cc");
    logger.error("{},{},{}", "aaa", "bbb", "cc");
  }


  @Test
  public void testLogger2() {
    
    
  }
}