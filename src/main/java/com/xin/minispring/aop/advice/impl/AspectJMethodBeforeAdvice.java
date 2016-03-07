package com.xin.minispring.aop.advice.impl;

import java.lang.reflect.Method;

import com.xin.minispring.aop.advice.MethodBeforeAdvice;

/**
 * @author zx150842@126.com
 *
 */
public class AspectJMethodBeforeAdvice extends AbstractAspectJAdvice implements MethodBeforeAdvice {

  @Override
  public void before(Method method, Object[] args, Object target) throws Exception {
    invokeAdviceMethod(getArgs(args));
  }

}
