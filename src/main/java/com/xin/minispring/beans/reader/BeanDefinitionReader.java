package com.xin.minispring.beans.reader;

public interface BeanDefinitionReader {

  void loadBeanDefinitions(String location) throws Exception;
}
