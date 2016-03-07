package com.xin.minispring.beans.processor;

import com.xin.minispring.aop.AopInfrastructureBean;

public interface BeanPostProcessor extends AopInfrastructureBean {

  Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;
  
  Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
