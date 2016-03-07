package com.xin.minispring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 通知类
 * 
 * @author zhangxin
 *
 */
public class PerformanceAdvice implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    System.out.println("around advice starting");
    Object retVal = invocation.proceed();
    System.out.println("around advice ending");
    return retVal;
  }

}
