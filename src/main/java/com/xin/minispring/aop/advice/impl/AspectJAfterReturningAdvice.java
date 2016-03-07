package com.xin.minispring.aop.advice.impl;

import java.lang.reflect.Method;

import com.xin.minispring.aop.advice.AfterReturningAdvice;

/**
 * @author zx150842@126.com
 *
 */
public class AspectJAfterReturningAdvice extends AbstractAspectJAdvice implements AfterReturningAdvice {

  @Override
  public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Exception {
    invokeAdviceMethod(getArgs(args));
  }

}
