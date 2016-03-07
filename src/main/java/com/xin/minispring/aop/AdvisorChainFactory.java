package com.xin.minispring.aop;

import java.lang.reflect.Method;
import java.util.List;

import com.xin.minispring.aop.support.Advised;

public interface AdvisorChainFactory {

  List<Object> getInterceptors(Advised config, Method method, Class<?> targetClass);
}
