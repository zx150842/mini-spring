package com.xin.minispring.beans.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xin.minispring.beans.BeanDefinition;
import com.xin.minispring.beans.exceptions.IllegalBeanException;
import com.xin.minispring.beans.processor.BeanPostProcessor;

/**
 * @author zx150842@126.com
 *
 */
public abstract class AbstractBeanFactory implements BeanFactory {

  private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();
  private List<String> beanNames = new ArrayList<String>();
  private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();
  
  @Override
  public Object getBean(String beanName) throws Exception {
    if (!beanDefinitionMap.containsKey(beanName)) {
      throw new IllegalBeanException("No bean named " + beanName + " is found");
    }
    BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
    Object bean = beanDefinition.getBean();
    if (bean == null) {
      bean = doCreateBean(beanDefinition);
      applyBeanProperties(beanDefinition);
      bean = doInitializeBean(bean, beanName);
      beanDefinition.setBean(bean);
    }
    return bean;
  }
  
  protected abstract void applyBeanProperties(BeanDefinition beanDefinition) throws Exception;
  
  public void registerBean(BeanDefinition beanDefinition) {
    // TODO 自动生成bean id
  }
  
  public void registerBean(String beanName, BeanDefinition beanDefinition) {
    if (beanDefinitionMap.containsKey(beanName)) {
      throw new IllegalBeanException("Multi beans have the same id: " + beanName);
    }
    beanDefinitionMap.put(beanName, beanDefinition);
    beanNames.add(beanName);
  }
  
  public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
    beanPostProcessors.add(beanPostProcessor);
  }
  
  public void loadSingletonBeans() throws Exception {
    for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
      String beanName = entry.getKey();
      getBean(beanName);
    }
  }
  
  @SuppressWarnings("unchecked")
  public <T> List<T> getBeansForType(Class<T> beanClass) throws Exception {
    List<T> beans = new ArrayList<T>();
    for (String beanName : beanNames) {
      BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
      if (beanClass.isAssignableFrom(beanDefinition.getBeanClass())) {
        beans.add((T)getBean(beanName));
      }
    }
    return beans;
  }
  
  private Object doCreateBean(BeanDefinition beanDefinition) {
    try {
      Object bean = beanDefinition.getBeanClass().newInstance();
      beanDefinition.setBean(bean);
      return bean;
    } catch (InstantiationException | IllegalAccessException e) {
      throw new IllegalBeanException("Cannot instance bean", e);
    }
  }
  
  private Object doInitializeBean(Object bean, String beanName) throws Exception {
    for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
      bean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
    }
    // TODO init bean
    for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
      bean = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
    }
    return bean;
  }
}
