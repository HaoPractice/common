package com.sf.test.util;

import org.junit.Test;

import com.sf.utils.Jsons;

public class GsonTest {
  @Test
  public void toJsonString() {
    GsonTestBean src = new GsonTestBean();
    src.a = "ddd";
    String json = Jsons.toJson(src);
    
    System.out.println(json);
    
    GsonTestBean fromJson = Jsons.fromJson(json, GsonTestBean.class);
    System.out.println(fromJson.getA());
    
  }
  static class GsonTestBean{
    private String  a = "aa"; 
    String getA(){
      return "vvv"+a;
    }
  }
}
