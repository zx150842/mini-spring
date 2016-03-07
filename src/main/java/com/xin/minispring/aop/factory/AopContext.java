package com.xin.minispring.aop.factory;

/**
 * @author zx150842@126.com
 *
 */
public abstract class AopContext {

  private static final ThreadLocal<Object> currentProxy = new ThreadLocal<Object>();
  
  public static Object currentProxy() {
    Object proxy = currentProxy.get();
    if (proxy == null) {
      throw new IllegalStateException("Cannot find current proxy: Set 'exposeProxy' property on Advised to 'true' to make it available.");
    }
    return proxy;
  }
  
  static Object setCurrentProxy(Object proxy) {
    Object old = currentProxy.get();
    if (proxy != null) {
      currentProxy.set(proxy);
    } else {
      currentProxy.remove();
    }
    return old;
  }
}
