package com.xin.minispring.beans.reader;

import java.util.HashMap;
import java.util.Map;

import com.xin.minispring.beans.BeanDefinition;

/**
 * @author zx150842@126.com
 *
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

  private Map<String, BeanDefinition> registry = new HashMap<String, BeanDefinition>();

  public Map<String, BeanDefinition> getRegistry() {
    return registry;
  }
}
