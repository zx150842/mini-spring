package com.xin.minispring.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aopalliance.intercept.Interceptor;

import com.xin.minispring.aop.adapter.AdvisorAdapterRegistry;
import com.xin.minispring.aop.adapter.impl.DefaultAdvisorAdapterRegistry;
import com.xin.minispring.aop.advisor.Advisor;
import com.xin.minispring.aop.advisor.PointcutAdvisor;
import com.xin.minispring.aop.pointcut.Pointcut;
import com.xin.minispring.aop.support.Advised;

/**
 * @author zx150842@126.com
 *
 */
public class DefaultAdvisorChainFactory implements AdvisorChainFactory {

  @Override
  public List<Object> getInterceptors(Advised config, Method method, Class<?> targetClass) {
    List<Object> interceptorList = new ArrayList<Object>(config.getAdvisors().length);
    AdvisorAdapterRegistry registry = new DefaultAdvisorAdapterRegistry();
    for (Advisor advisor : config.getAdvisors()) {
      if (advisor instanceof PointcutAdvisor) {
        Pointcut pointcut = ((PointcutAdvisor)advisor).getPointcut();
        if (pointcut.getClassFilter().matches(targetClass) 
            && pointcut.getMethodMatcher().matcher(method, targetClass)) {
          Interceptor[] interceptors = registry.getInterceptors(advisor);
          interceptorList.addAll(Arrays.asList(interceptors));
        }
      }
    }
    return interceptorList;
  }

}
