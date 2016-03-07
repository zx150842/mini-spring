package com.xin.minispring.beans;

import java.util.Properties;

import com.xin.minispring.beans.io.Resources;

/**
 * @author zx150842@126.com
 *
 */
public class BeanDefinition {

  private Object bean;
  private Class<?> beanClass;
  private String className;
  private Properties properties = new Properties();

  public Object getBean() {
    return bean;
  }

  public void setBean(Object bean) {
    this.bean = bean;
  }

  public Class<?> getBeanClass() {
    return beanClass;
  }

  public void setBeanClass(Class<?> beanClass) {
    this.beanClass = beanClass;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
    beanClass = Resources.classForName(className);
  }

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }
}
