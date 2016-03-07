package com.xin.minispring.aop.adapter.impl;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import com.xin.minispring.aop.adapter.AdvisorAdapter;
import com.xin.minispring.aop.adapter.AdvisorAdapterRegistry;
import com.xin.minispring.aop.adapter.UnknownAdviceTypeException;
import com.xin.minispring.aop.advisor.Advisor;
import com.xin.minispring.aop.advisor.impl.DefaultPointcutAdvisor;

/**
 * @author zx150842@126.com
 *
 */
public class DefaultAdvisorAdapterRegistry implements AdvisorAdapterRegistry {

  private final List<AdvisorAdapter> adapters = new ArrayList<AdvisorAdapter>();
  
  public DefaultAdvisorAdapterRegistry() {
    registerAdvisorAdapter(new MethodBeforeAdviceAdaptor());
    registerAdvisorAdapter(new AfterReturningAdviceAdapter());
    registerAdvisorAdapter(new ThrowsAdviceAdaptor());
  }
  
  @Override
  public Advisor wrap(Object adviceObject) {
    if (adviceObject instanceof Advisor) {
      return (Advisor)adviceObject;
    }
    if (!(adviceObject instanceof Advice)) {
      throw new UnknownAdviceTypeException(adviceObject);
    }
    Advice advice = (Advice)adviceObject;
    if (advice instanceof MethodInterceptor) {
      return new DefaultPointcutAdvisor(advice);
    }
    for (AdvisorAdapter adapter : adapters) {
      if (adapter.supportsAdvice(advice)) {
        return new DefaultPointcutAdvisor(advice);
      }
    }
    throw new UnknownAdviceTypeException(advice);
  }

  @Override
  public MethodInterceptor[] getInterceptors(Advisor advisor) {
    List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>();
    Advice advice = advisor.getAdvice();
    if (advice instanceof MethodInterceptor) {
      interceptors.add((MethodInterceptor)advice);
    }
    for (AdvisorAdapter adapter : adapters) {
      if (adapter.supportsAdvice(advice)) {
        interceptors.add(adapter.getInterceptor(advisor));
      }
    }
    if (interceptors.isEmpty()) {
      throw new UnknownAdviceTypeException(advice);
    }
    return interceptors.toArray(new MethodInterceptor[interceptors.size()]);
  }

  @Override
  public void registerAdvisorAdapter(AdvisorAdapter adapter) {
    adapters.add(adapter);
  }

}
