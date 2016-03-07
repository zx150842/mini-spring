package com.xin.minispring.aop.target;


public interface TargetSource extends TargetClassAware {

  Object getTarget();
}
