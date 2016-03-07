package com.xin.minispring.aop.factory;

import com.xin.minispring.aop.support.AdvisedSupport;

/**
 * @author zx150842@126.com
 *
 */
public class DefaultAopProxyFactory implements AopProxyFactory {

  @Override
  public AopProxy createAopProxy(AdvisedSupport config) {
    if (config.isOptimize() || config.isProxyTargetClass()) {
      return new CglibAopProxy(config);
    } else {
      return new JdkDynamicAopProxy(config);
    }
  }

}
