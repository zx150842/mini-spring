package com.xin.minispring.aop.adapter.impl;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import com.xin.minispring.aop.adapter.AdvisorAdapter;
import com.xin.minispring.aop.advice.MethodBeforeAdvice;
import com.xin.minispring.aop.advisor.Advisor;
import com.xin.minispring.aop.interceptor.MethodBeforeAdviceInterceptor;

/**
 * @author zx150842@126.com
 *
 */
public class MethodBeforeAdviceAdaptor implements AdvisorAdapter {

  @Override
  public boolean supportsAdvice(Advice advice) {
    return (advice instanceof MethodBeforeAdvice);
  }

  @Override
  public MethodInterceptor getInterceptor(Advisor advisor) {
    MethodBeforeAdvice advice = (MethodBeforeAdvice)advisor.getAdvice();
    return new MethodBeforeAdviceInterceptor(advice);
  }

}
