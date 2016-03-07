package com.xin.minispring.bean;

import com.xin.minispring.aop.factory.AopContext;


public class HelloWorldServiceImpl implements HelloWorldService {

  private OutputService outputService;
  private String text;
  
  public void helloWorld() {
    outputService.output(text);
  }
  
  public void exceptionMethod() {
    outputService.output("this is a method that throws an exception");
    throw new RuntimeException("exception!");
  }
  
  public void unInterceptMethod() {
    outputService.output("this is an unIntercept method");
  }
  
  public void unInterceptMethodWithInterceptMethodInternal() {
    outputService.output("this is an unIntercept method with intercept method internal");
//    helloWorld();
    ((HelloWorldService)AopContext.currentProxy()).helloWorld();
  }
  
  public void unInterfaceMethod() {
    outputService.output("this is an unInterface method");
  }
}
