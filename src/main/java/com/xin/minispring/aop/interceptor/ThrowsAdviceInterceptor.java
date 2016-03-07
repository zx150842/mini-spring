package com.xin.minispring.aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.xin.minispring.aop.advice.ThrowsAdvice;

/**
 * @author zx150842@126.com
 *
 */
public class ThrowsAdviceInterceptor implements MethodInterceptor {

  private final ThrowsAdvice throwsAdvice;


  public ThrowsAdviceInterceptor(ThrowsAdvice throwsAdvice) {
    this.throwsAdvice = throwsAdvice;
  }
  
  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    try {
      return invocation.proceed();
    } catch (Throwable ex) {
      throwsAdvice.afterThrowing(invocation.getMethod(), invocation.getArguments(), invocation.getThis(), ex);
      throw ex;
    }
  }
}
