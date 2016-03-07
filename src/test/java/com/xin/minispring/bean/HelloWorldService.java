package com.xin.minispring.bean;

public interface HelloWorldService {

  void helloWorld();
  
  void exceptionMethod();
  
  void unInterceptMethod();
  
  void unInterceptMethodWithInterceptMethodInternal();
}
