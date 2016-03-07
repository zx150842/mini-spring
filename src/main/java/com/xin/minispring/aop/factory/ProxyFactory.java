package com.xin.minispring.aop.factory;

import com.xin.minispring.aop.support.AdvisedSupport;

/**
 * @author zx150842@126.com
 *
 */
public class ProxyFactory extends AdvisedSupport {

  private AopProxyFactory aopProxyFactory;
  
  public ProxyFactory() {}
  
  public ProxyFactory(Object target) {
    setTarget(target);
  }
  
  public Object getProxy() {
    return createAopProxy().getProxy();
  }
  
  public Object getProxy(ClassLoader classLoader) {
    return createAopProxy().getProxy(classLoader);
  }
  
  protected final AopProxy createAopProxy() {
    return getAopProxyFactory().createAopProxy(this);
  }

  public AopProxyFactory getAopProxyFactory() {
    return aopProxyFactory;
  }

  public void setAopProxyFactory(AopProxyFactory aopProxyFactory) {
    this.aopProxyFactory = aopProxyFactory;
  }
  
  
}
