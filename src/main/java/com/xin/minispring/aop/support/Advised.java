package com.xin.minispring.aop.support;

import com.xin.minispring.aop.advisor.Advisor;
import com.xin.minispring.aop.target.TargetClassAware;
import com.xin.minispring.aop.target.TargetSource;

public interface Advised extends TargetClassAware {

  boolean isFrozen();
  
  boolean isProxyTargetClass();
  
  Class<?>[] getProxiedInterfaces();
  
  boolean isInterfaceProxied(Class<?> intf);
  
  void setTargetSource(TargetSource targetSource);
  
  TargetSource getTargetSource();
  
  void setExposeProxy(boolean exposeProxy);
  
  boolean isExposeProxy();
  
  Advisor[] getAdvisors();
  
  void addAdvisor(Advisor advisor);
  
  void addAdvisor(int pos, Advisor advisor);
  
}
