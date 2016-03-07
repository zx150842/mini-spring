package com.xin.minispring.beans.factory;

public interface BeanFactoryAware extends Aware {

  void setBeanFactory(BeanFactory beanFactory);
}
