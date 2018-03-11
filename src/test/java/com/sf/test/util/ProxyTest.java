package com.sf.test.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

import org.junit.Test;

import com.google.common.collect.Lists;

public class ProxyTest {
  interface AdviceI{
    void before();
    void after();
  }
  @Test
  public void testProxy(){
    Collection<String> sourceCollection = Lists.newArrayList();
    
    AdviceI adviceI = new AdviceI() {
      
      @Override
      public void before() {
        System.err.println("before");
      }
      
      @Override
      public void after() {
        System.err.println("after");
      }
    };
    
    Collection<String> newProxyInstance =getProxy(adviceI, sourceCollection);
    newProxyInstance.add("1");
    System.out.println(newProxyInstance.getClass());
    System.out.println(newProxyInstance.size());
    System.out.println(newProxyInstance.toString());
  }
  private <T>T getProxy(final AdviceI adviceI, final T t) {
    InvocationHandler invocationHandler = new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        adviceI.before();
        Object invoke = method.invoke(t, args);
        adviceI.after();
        return invoke;
      }
    };
    @SuppressWarnings("unchecked")
    T newProxyInstance = (T) Proxy.newProxyInstance(getClass().getClassLoader(), t.getClass().getInterfaces(), invocationHandler);
    
    return newProxyInstance;
  }
  
}
