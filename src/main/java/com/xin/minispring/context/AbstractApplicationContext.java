package com.xin.minispring.context;

import java.util.List;

import com.xin.minispring.beans.factory.AbstractBeanFactory;
import com.xin.minispring.beans.processor.BeanPostProcessor;

/**
 * @author zx150842@126.com
 *
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

  private AbstractBeanFactory beanFactory;
  
  public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }
  
  @Override
  public Object getBean(String beanName) throws Exception {
    return beanFactory.getBean(beanName);
  }
  
  public void refresh() throws Exception {
    loadBeanDefinitions();
    addBeanPostProcessors();
    beanFactory.loadSingletonBeans();
  }
  
  public abstract void loadBeanDefinitions() throws Exception;
  
  private void addBeanPostProcessors() throws Exception {
    List<BeanPostProcessor> list = beanFactory.getBeansForType(BeanPostProcessor.class);
    for (Object beanPostProcessor : list) {
      beanFactory.addBeanPostProcessor((BeanPostProcessor)beanPostProcessor);
    }
  }

  public AbstractBeanFactory getBeanFactory() {
    return beanFactory;
  }
}
