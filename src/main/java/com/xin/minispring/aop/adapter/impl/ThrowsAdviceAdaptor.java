package com.xin.minispring.aop.adapter.impl;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import com.xin.minispring.aop.adapter.AdvisorAdapter;
import com.xin.minispring.aop.advice.ThrowsAdvice;
import com.xin.minispring.aop.advisor.Advisor;
import com.xin.minispring.aop.interceptor.ThrowsAdviceInterceptor;

/**
 * @author zx150842@126.com
 *
 */
public class ThrowsAdviceAdaptor implements AdvisorAdapter {

  @Override
  public boolean supportsAdvice(Advice advice) {
    return (advice instanceof ThrowsAdvice);
  }

  @Override
  public MethodInterceptor getInterceptor(Advisor advisor) {
    ThrowsAdvice advice = (ThrowsAdvice)advisor.getAdvice();
    return new ThrowsAdviceInterceptor(advice);
  }

}
