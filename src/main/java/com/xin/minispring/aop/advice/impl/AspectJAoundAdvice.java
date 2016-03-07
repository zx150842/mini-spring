package com.xin.minispring.aop.advice.impl;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author zx150842@126.com
 *
 */
public class AspectJAoundAdvice extends AbstractAspectJAdvice implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    return invokeAdviceMethod(getArgs(invocation.getArguments()));
  }

}
