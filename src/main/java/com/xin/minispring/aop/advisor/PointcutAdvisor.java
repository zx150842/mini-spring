package com.xin.minispring.aop.advisor;

import com.xin.minispring.aop.pointcut.Pointcut;

public interface PointcutAdvisor extends Advisor {

  Pointcut getPointcut();
}
