package com.xin.minispring.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.aopalliance.aop.Advice;

import com.xin.minispring.aop.advisor.Advisor;
import com.xin.minispring.aop.advisor.PointcutAdvisor;
import com.xin.minispring.aop.factory.DefaultAopProxyFactory;
import com.xin.minispring.aop.factory.ProxyFactory;
import com.xin.minispring.aop.pointcut.Pointcut;
import com.xin.minispring.aop.target.TargetSource;
import com.xin.minispring.aop.target.impl.SingletonTargetSource;
import com.xin.minispring.beans.factory.AbstractBeanFactory;
import com.xin.minispring.beans.factory.BeanFactory;
import com.xin.minispring.beans.factory.BeanFactoryAware;
import com.xin.minispring.beans.io.ClassLoaderWrapper;
import com.xin.minispring.beans.processor.BeanPostProcessor;

/**
 * @author zx150842@126.com
 *
 */
public class DefaultAdvisorAutoProxyCreator extends ProxyConfig implements BeanPostProcessor, BeanFactoryAware {

  private AbstractBeanFactory beanFactory;
  
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
    Class<?> beanClass = bean.getClass();
    if (shouldSkip(beanClass)) {
      return bean;
    }
    TargetSource targetSource = new SingletonTargetSource(bean);
    List<Advisor> advisors = findEligibleAdvisors(beanClass, beanName);
    if (advisors == null || advisors.isEmpty()) {
      return bean;
    }
    Object proxy = createProxy(beanClass, beanName, advisors, targetSource);
    return proxy;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
    return bean;
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) {
    this.beanFactory = (AbstractBeanFactory)beanFactory;
  }
  
  private boolean shouldSkip(Class<?> clazz) {
    return Advice.class.isAssignableFrom(clazz) 
        || Pointcut.class.isAssignableFrom(clazz) 
        || Advisor.class.isAssignableFrom(clazz);
  }

  private List<Advisor> findEligibleAdvisors(Class<?> beanClass, String beanName) throws Exception {
    List<Advisor> candidateAdvisors = beanFactory.getBeansForType(Advisor.class);
    List<Advisor> eligibleAdvisors = findAdvisorsThatCanApply(candidateAdvisors, beanClass, beanName);
    // TODO sortAdvisors
    Collections.sort(eligibleAdvisors, new Comparator<Advisor>() {

      @Override
      public int compare(Advisor o1, Advisor o2) {
        if (o1.getOrder() <= o2.getOrder()) {
          return -1;
        }
        return 1;
      }});
    return eligibleAdvisors;
  }
  
  private List<Advisor> findAdvisorsThatCanApply(List<Advisor> candidateAdvisors, Class<?> beanClass, String beanName) {
    List<Advisor> advisors = new ArrayList<Advisor>();
    for (Advisor advisor : candidateAdvisors) {
      if (advisor instanceof PointcutAdvisor) {
        PointcutAdvisor pointcutAdvisor = (PointcutAdvisor)advisor;
        Pointcut pointcut = pointcutAdvisor.getPointcut();
        if (pointcut.getClassFilter().matches(beanClass)) {
          // TODO getAllInterfacesForClassAsSet
          Method[] methods = beanClass.getDeclaredMethods();
          for (Method method : methods) {
            if (pointcut.getMethodMatcher().matcher(method, beanClass)) {
              advisors.add(advisor);
              continue;
            }
          }
        }
      }
    }
    return advisors;
  }
  
  private Object createProxy(Class<?> beanClass, String beanName, List<Advisor> advisors, TargetSource targetSource) {
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.copyFrom(this);
    if (!proxyFactory.isProxyTargetClass()) {
      evaluateProxyInterfaces(beanClass, proxyFactory);
    }
    for (Advisor advisor : advisors) {
      proxyFactory.addAdvisor(advisor);
    }
    proxyFactory.setTargetSource(targetSource);
    proxyFactory.setAopProxyFactory(new DefaultAopProxyFactory());
    return proxyFactory.getProxy(ClassLoaderWrapper.getDefaultClassLoader());
  }
  
  public Class<?>[] getAllInterfacesForClass(Class<?> clazz, ClassLoader classLoader) {
    Set<Class<?>> ifcs = getAllInterfacesForClassAsSet(clazz, classLoader);
    return ifcs.toArray(new Class<?>[ifcs.size()]);
  }
  
  protected void evaluateProxyInterfaces(Class<?> beanClass, ProxyFactory proxyFactory) {
    Class<?>[] targetInterfaces = getAllInterfacesForClass(beanClass, ClassLoaderWrapper.getDefaultClassLoader());
    boolean hasReasonableProxyInterface = false;
    for (Class<?> ifc : targetInterfaces) {
      if (ifc.getMethods().length > 0) {
        hasReasonableProxyInterface = true;
        break;
      }
    }
    if (hasReasonableProxyInterface) {
      for (Class<?> ifc : targetInterfaces) {
        proxyFactory.addInterface(ifc);
      }
    } else {
      proxyFactory.setProxyTargetClass(true);
    }
  }
  
  public Set<Class<?>> getAllInterfacesForClassAsSet(Class<?> clazz, ClassLoader classLoader) {
    if (clazz.isInterface()) {
      return Collections.<Class<?>>singleton(clazz);
    }
    Set<Class<?>> interfaces = new LinkedHashSet<Class<?>>();
    while (clazz != null) {
      Class<?>[] ifcs = clazz.getInterfaces();
      for (Class<?> ifc : ifcs) {
        interfaces.addAll(getAllInterfacesForClassAsSet(ifc, classLoader));
      }
      clazz = clazz.getSuperclass();
    }
    return interfaces;
  }
}
