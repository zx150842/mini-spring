package com.xin.minispring.aop.factory;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author zx150842@126.com
 *
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

  protected Object proxy;
  protected Object target;
  protected Class<?> targetClass;
  protected Method method;
  protected Object[] arguments;
  protected List<Object> interceptors;
  private int currentInterceptorIndex = -1;
  
  protected ReflectiveMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class<?> targetClass, List<Object> interceptors) {
    this.proxy = proxy;
    this.target = target;
    // TODO 找到桥接方法的原方法
    this.method = method;
    this.arguments = arguments;
    this.interceptors = interceptors;
  }
  
  public final Object getProxy() {
    return proxy;
  }
  
  public final Object getThis() {
    return target;
  }
  
  public final Method getMethod() {
    return method;
  }
  
  public final Object[] getArguments() {
    return this.arguments != null ? arguments : new Object[0];
  }
  
  public Object proceed() throws Throwable {
    if (currentInterceptorIndex == interceptors.size() - 1) {
      return invokeJoinpoint();
    }
    Object interceptor = interceptors.get(++currentInterceptorIndex);
    return ((MethodInterceptor)interceptor).invoke(this);
  }
  
  protected Object invokeJoinpoint() throws Throwable {
    if ((!Modifier.isPublic(method.getModifiers()) 
        || !Modifier.isPublic(method.getDeclaringClass().getModifiers())) 
        && !method.isAccessible()) {
      method.setAccessible(true);
    }
    return method.invoke(target, arguments);
  }

  @Override
  public AccessibleObject getStaticPart() {
    return method;
  }
}
