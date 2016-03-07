package com.xin.minispring.aop.advice;

import java.lang.reflect.Method;

public interface ThrowsAdvice extends AfterAdvice {

  void afterThrowing(Method method, Object[] args, Object target, Throwable ex) throws Throwable;
}
