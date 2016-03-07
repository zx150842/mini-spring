package com.xin.minispring.aop.adapter;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import com.xin.minispring.aop.advisor.Advisor;

public interface AdvisorAdapter {

  boolean supportsAdvice(Advice advice);
  
  MethodInterceptor getInterceptor(Advisor advisor);
}
