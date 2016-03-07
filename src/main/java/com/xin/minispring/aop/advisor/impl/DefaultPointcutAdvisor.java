package com.xin.minispring.aop.advisor.impl;

import org.aopalliance.aop.Advice;

import com.xin.minispring.aop.advisor.AbstractPointcutAdvisor;
import com.xin.minispring.aop.advisor.PointcutAdvisor;
import com.xin.minispring.aop.pointcut.Pointcut;
import com.xin.minispring.aop.pointcut.impl.TruePointcut;

/**
 * @author zx150842@126.com
 *
 */
public class DefaultPointcutAdvisor extends AbstractPointcutAdvisor implements PointcutAdvisor {

  private Pointcut pointcut;
  
  public DefaultPointcutAdvisor(Advice advice) {
    this(defaultPointcut, advice);
  }
  
  public DefaultPointcutAdvisor(Pointcut pointcut, Advice advice) {
    this.pointcut = pointcut;
    setAdvice(advice);
  }
  
  @Override
  public Pointcut getPointcut() {
    return pointcut;
  }

  public void setPointcut(Pointcut pointcut) {
    this.pointcut = pointcut;
  }
  
  public static final Pointcut defaultPointcut = new TruePointcut();
}
