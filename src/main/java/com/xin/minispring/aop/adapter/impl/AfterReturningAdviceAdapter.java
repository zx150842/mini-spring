package com.xin.minispring.aop.adapter.impl;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import com.xin.minispring.aop.adapter.AdvisorAdapter;
import com.xin.minispring.aop.advice.AfterReturningAdvice;
import com.xin.minispring.aop.advisor.Advisor;
import com.xin.minispring.aop.interceptor.AfterReturningAdviceInterceptor;

/**
 * @author zx150842@126.com
 *
 */
public class AfterReturningAdviceAdapter implements AdvisorAdapter {

  @Override
  public boolean supportsAdvice(Advice advice) {
    return (advice instanceof AfterReturningAdvice);
  }

  @Override
  public MethodInterceptor getInterceptor(Advisor advisor) {
    AfterReturningAdvice advice = (AfterReturningAdvice)advisor.getAdvice();
    return new AfterReturningAdviceInterceptor(advice);
  }

}
