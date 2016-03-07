package com.xin.minispring.aop.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import com.xin.minispring.aop.support.AdvisedSupport;
import com.xin.minispring.beans.io.ClassLoaderWrapper;

/**
 * @author zx150842@126.com
 *
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

  private final AdvisedSupport advised;
  
  public JdkDynamicAopProxy(AdvisedSupport config) {
    this.advised = config;
  }
  
  @Override
  public Object getProxy() {
    return getProxy(ClassLoaderWrapper.getDefaultClassLoader());
  }

  @Override
  public Object getProxy(ClassLoader classLoader) {
    Class<?>[] proxiedInterfaces = advised.getProxiedInterfaces();
    return Proxy.newProxyInstance(classLoader, proxiedInterfaces, this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    
    Object oldProxy = null;
    boolean setProxyContext = false;
    try {
      if (advised.getExposeProxy()) {
        oldProxy = AopContext.setCurrentProxy(proxy);
        setProxyContext = true;
      }
      Object retVal;
      Class<?> targetClass = advised.getTargetClass();
      Object target = advised.getTargetSource().getTarget();
      List<Object> chain = advised.getInterceptors(method, targetClass);
      ReflectiveMethodInvocation invocation = new ReflectiveMethodInvocation(proxy, target, method, args, targetClass, chain);
      if (chain.isEmpty()) {
        retVal = invocation.invokeJoinpoint();
      } else {
        retVal = invocation.proceed();
      }
      return retVal;
    } finally {
      if (setProxyContext) {
        AopContext.setCurrentProxy(oldProxy);
      }
    }
  }

}
