package com.xin.minispring.aop.adapter;

import org.aopalliance.intercept.MethodInterceptor;

import com.xin.minispring.aop.advisor.Advisor;

public interface AdvisorAdapterRegistry {

  Advisor wrap(Object advice);
  
  MethodInterceptor[] getInterceptors(Advisor advisor);
  
  void registerAdvisorAdapter(AdvisorAdapter adapter);
}
