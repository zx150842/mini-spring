package com.xin.minispring.aop.factory;

import com.xin.minispring.aop.support.AdvisedSupport;

public interface AopProxyFactory {

  AopProxy createAopProxy(AdvisedSupport config);
}
