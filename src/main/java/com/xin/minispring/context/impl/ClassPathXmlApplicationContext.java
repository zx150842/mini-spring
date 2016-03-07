package com.xin.minispring.context.impl;

import java.util.Map;

import com.xin.minispring.beans.BeanDefinition;
import com.xin.minispring.beans.factory.AbstractBeanFactory;
import com.xin.minispring.beans.factory.impl.AutowireCapableBeanFactory;
import com.xin.minispring.beans.reader.AbstractBeanDefinitionReader;
import com.xin.minispring.beans.reader.impl.XmlBeanDefinitionReader;
import com.xin.minispring.context.AbstractApplicationContext;

/**
 * @author zx150842@126.com
 *
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

  private String configLocation;
  
  public ClassPathXmlApplicationContext(String configLocation) throws Exception {
    this(configLocation, new AutowireCapableBeanFactory());
  }
  
  public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
    super(beanFactory);
    this.configLocation = configLocation;
    refresh();
  }
  
  @Override
  public void loadBeanDefinitions() throws Exception {
    AbstractBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader();
    beanDefinitionReader.loadBeanDefinitions(configLocation);
    for (Map.Entry<String, BeanDefinition> entry : beanDefinitionReader.getRegistry().entrySet()) {
      getBeanFactory().registerBean(entry.getKey(), entry.getValue());
    }
  }
}
