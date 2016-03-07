package com.xin.minispring.aop.pointcut.impl;

import java.lang.reflect.Method;

import com.xin.minispring.aop.ClassFilter;
import com.xin.minispring.aop.MethodMatcher;
import com.xin.minispring.aop.pointcut.Pointcut;

/**
 * @author zx150842@126.com
 *
 */
public class TruePointcut implements Pointcut, ClassFilter, MethodMatcher {

  @Override
  public ClassFilter getClassFilter() {
    return this;
  }

  @Override
  public MethodMatcher getMethodMatcher() {
    return this;
  }

  @Override
  public boolean matcher(Method method, Class<?> targetClass) {
    return true;
  }

  @Override
  public boolean matches(Class<?> clazz) {
    return true;
  }

}
