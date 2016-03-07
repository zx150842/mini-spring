package com.xin.minispring.aop.factory;

/**
 * @author zx150842@126.com
 *
 */
public interface AopProxy {

  Object getProxy();
  
  Object getProxy(ClassLoader classLoader);
}
