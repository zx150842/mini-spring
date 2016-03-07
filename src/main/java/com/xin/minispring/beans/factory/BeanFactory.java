package com.xin.minispring.beans.factory;

public interface BeanFactory {

  Object getBean(String beanName) throws Exception;
}
