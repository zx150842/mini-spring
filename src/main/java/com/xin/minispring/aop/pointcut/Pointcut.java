package com.xin.minispring.aop.pointcut;

import com.xin.minispring.aop.ClassFilter;
import com.xin.minispring.aop.MethodMatcher;

public interface Pointcut {

  ClassFilter getClassFilter();
  
  MethodMatcher getMethodMatcher();
}
