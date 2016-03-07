package com.xin.minispring.aop.advice.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.xin.minispring.aop.advice.ThrowsAdvice;

/**
 * @author zx150842@126.com
 *
 */
public class AspectJAfterThrowingAdvice extends AbstractAspectJAdvice implements ThrowsAdvice {

  private static final String AFTER_THROWING = "afterThrowing";
  
  private final Map<Class<?>, Method> exceptionHandlerMap = new HashMap<Class<?>, Method>();
  
  public void afterThrowing(Method method, Object[] args, Object target, Throwable ex) throws Throwable {
    Method handler = getExceptionHandler(ex);
    Object[] handlerArgs = getHandlerArgs(ex);
    if (handler != null) {
      invokeAdviceMethod(handler, handlerArgs);
    }
  }
  
  public Method getExceptionHandler(Throwable ex) throws Exception {
    Class<?> exceptionClass = ex.getClass(); 
    checkReady();
    Method handler = exceptionHandlerMap.get(exceptionClass);
    while (handler == null && exceptionClass != Throwable.class) {
      exceptionClass = exceptionClass.getSuperclass();
      handler = exceptionHandlerMap.get(exceptionClass);
    }
    return handler;
  }
  
  protected Object[] getHandlerArgs(Throwable ex) {
    // 参数拼装
    return new Object[] { ex };
  }

  protected void checkReady() throws Exception {
    Object aspectBeanInstance = getAspectBean();
    Method[] methods = aspectBeanInstance.getClass().getDeclaredMethods();
    for (Method method : methods) {
      if (method.getName().equals(AFTER_THROWING)
          && (method.getParameterTypes().length == 1)
          && Throwable.class.isAssignableFrom(method.getParameterTypes()[method.getParameterTypes().length - 1])) {
        exceptionHandlerMap.put(method.getParameterTypes()[method.getParameterTypes().length - 1],
            method);
      }
    }
    if (this.exceptionHandlerMap.isEmpty()) {
      throw new IllegalArgumentException("At least one handler method must be found in class ["
          + aspectBeanInstance.getClass() + "]");
    }
  }
}
