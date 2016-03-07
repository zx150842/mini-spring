package com.xin.minispring.aop.advice.impl;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;

import com.xin.minispring.beans.factory.BeanFactory;
import com.xin.minispring.beans.factory.BeanFactoryAware;

/**
 * @author zx150842@126.com
 *
 */
public abstract class AbstractAspectJAdvice implements Advice, BeanFactoryAware {

  private Class<?> declaringClass;
  private String methodName;
  private Class<?>[] parameterTypes;
  private Method aspectJAdviceMethod;
  private String aspectBeanName;
  private BeanFactory beanFactory;
  
  protected Object invokeAdviceMethod(Object[] args) throws Exception {
    checkReadyToInvoke();
    return invokeAdviceMethod(aspectJAdviceMethod, args);
  }
  
  protected Object invokeAdviceMethod(Method method, Object[] args) throws Exception {
    Object aspectInstance = getAspectBean();
    return method.invoke(aspectInstance, args);
  }
  
  protected void checkReadyToInvoke() throws Exception {
    if (aspectJAdviceMethod == null) {
      synchronized (this) {
        if (aspectJAdviceMethod == null) {
          Object aspectInstance = getAspectBean();
          declaringClass = aspectInstance.getClass();
          aspectJAdviceMethod = declaringClass.getMethod(methodName, parameterTypes);
        }
      }
    }
  }
  
  protected Object getAspectBean() throws Exception {
    return beanFactory.getBean(aspectBeanName);
  }
  
  protected Object[] getArgs(Object[] args) {
    return null;
  }
  
  public void setBeanFactory(BeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }

  public Method getAspectJAdviceMethod() {
    return aspectJAdviceMethod;
  }

  public void setAspectJAdviceMethod(Method aspectJAdviceMethod) {
    this.aspectJAdviceMethod = aspectJAdviceMethod;
  }

  public String getAspectBeanName() {
    return aspectBeanName;
  }

  public void setAspectBeanName(String aspectBeanName) {
    this.aspectBeanName = aspectBeanName;
  }

  public Class<?> getDeclaringClass() {
    return declaringClass;
  }

  public String getMethodName() {
    return methodName;
  }

  public Class<?>[] getParameterTypes() {
    return parameterTypes;
  }
}
