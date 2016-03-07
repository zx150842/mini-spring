package com.xin.minispring.aop.factory;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.xin.minispring.aop.support.AdvisedSupport;

/**
 * @author zx150842@126.com
 *
 */
public class CglibAopProxy implements AopProxy {

  private final AdvisedSupport advised;
  
  public CglibAopProxy(AdvisedSupport config) {
    this.advised = config;
  }
  
  @Override
  public Object getProxy() {
    return getProxy(null);
  }

  @Override
  public Object getProxy(ClassLoader classLoader) {
    Class<?> targetClass = advised.getTargetClass();
    if (targetClass.isInterface()) {
      advised.setInterfaces(targetClass);
    }
    Class<?>[] proxiedInterfaces = advised.getProxiedInterfaces();
    Enhancer enhancer = new Enhancer();
    if (classLoader != null) {
      enhancer.setClassLoader(classLoader);
    }
    enhancer.setSuperclass(targetClass);
    enhancer.setInterfaces(proxiedInterfaces);
    enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
    Object enhanced = enhancer.create();
    return enhanced;
  }
  
  private static class DynamicAdvisedInterceptor implements MethodInterceptor {
    
    private final AdvisedSupport advised;
    
    public DynamicAdvisedInterceptor(AdvisedSupport advised) {
      this.advised = advised;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
        throws Throwable {
      Object oldProxy = null;
      boolean setProxyContext = false;
      try {
        if (advised.getExposeProxy()) {
          oldProxy = AopContext.setCurrentProxy(proxy);
          setProxyContext = true;
        }
        Class<?> targetClass = advised.getTargetClass();
        Object target = advised.getTargetSource().getTarget();
        List<Object> chain = advised.getInterceptors(method, targetClass);
        Object retVal;
        if (chain.isEmpty()) {
          retVal = methodProxy.invoke(target, args);
        } else {
          retVal = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
        }
        return retVal;
      } finally {
        if (setProxyContext) {
          AopContext.setCurrentProxy(oldProxy);
        }
      }
    }
  }
  
  private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
    private final MethodProxy methodProxy;
    private final boolean publicMethod;
    
    public CglibMethodInvocation(Object proxy, Object target, Method method, Object[] arguments,
        Class<?> targetClass, List<Object> chain, MethodProxy methodProxy) {
      super(proxy, target, method, arguments, targetClass, chain);
      this.methodProxy = methodProxy;
      this.publicMethod = Modifier.isPublic(method.getModifiers());
    }
    
    @Override
    protected Object invokeJoinpoint() throws Throwable {
      if (publicMethod) {
        return methodProxy.invoke(target, arguments);
      } else {
        return super.invokeJoinpoint();
      }
    }
  }

}
