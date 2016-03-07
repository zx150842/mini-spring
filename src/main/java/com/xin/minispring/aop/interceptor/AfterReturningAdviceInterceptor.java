package com.xin.minispring.aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.xin.minispring.aop.advice.AfterReturningAdvice;

/**
 * @author zx150842@126.com
 *
 */
public class AfterReturningAdviceInterceptor implements MethodInterceptor {

  private final AfterReturningAdvice advice;
  
  public AfterReturningAdviceInterceptor(AfterReturningAdvice advice) {
    this.advice = advice;
  }

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    Object retVal = invocation.proceed();
    advice.afterReturning(retVal, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
    return retVal;
  }
  
}
