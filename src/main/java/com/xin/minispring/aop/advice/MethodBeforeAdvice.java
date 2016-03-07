package com.xin.minispring.aop.advice;

import java.lang.reflect.Method;

public interface MethodBeforeAdvice extends BeforeAdvice {

  void before(Method method, Object[] args, Object target) throws Exception;
}
