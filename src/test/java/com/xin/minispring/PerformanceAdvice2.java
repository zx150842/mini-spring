package com.xin.minispring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PerformanceAdvice2 implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    System.out.println("around advice2 starting");
    Object retVal = invocation.proceed();
    System.out.println("around advice2 ending");
    return retVal;
  }

}
