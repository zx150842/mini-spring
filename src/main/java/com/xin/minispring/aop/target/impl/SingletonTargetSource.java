package com.xin.minispring.aop.target.impl;

import com.xin.minispring.aop.target.TargetSource;

/**
 * @author zx150842@126.com
 *
 */
public class SingletonTargetSource implements TargetSource {

  private final Object target;
  
  public SingletonTargetSource(Object target) {
    this.target = target;
  }
  
  @Override
  public Class<?> getTargetClass() {
    return target.getClass();
  }

  @Override
  public Object getTarget() {
    return target;
  }

}
