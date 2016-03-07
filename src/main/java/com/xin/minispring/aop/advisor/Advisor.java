package com.xin.minispring.aop.advisor;

import org.aopalliance.aop.Advice;

import com.xin.minispring.aop.Ordered;

public interface Advisor extends Ordered {

  Advice getAdvice();
}
