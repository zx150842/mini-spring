package com.xin.minispring.aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.xin.minispring.aop.advice.MethodBeforeAdvice;

/**
 * @author zx150842@126.com
 *
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

  private MethodBeforeAdvice advice;
  
  public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
    this.advice = advice;
  }

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    advice.before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
    return invocation.proceed();
  }
  
}
