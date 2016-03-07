package com.xin.minispring.aop.advisor.impl;

import org.aopalliance.aop.Advice;

import com.xin.minispring.aop.advisor.AbstractPointcutAdvisor;
import com.xin.minispring.aop.pointcut.Pointcut;
import com.xin.minispring.aop.pointcut.impl.AspectJExpressionPointcut;

/**
 * @author zx150842@126.com
 *
 */
public class AspectJExpressionPointcutAdvisor extends AbstractPointcutAdvisor {

  private AspectJExpressionPointcut pointcut;

  public AspectJExpressionPointcutAdvisor() {}
  
  public AspectJExpressionPointcutAdvisor(Advice advice) {
    setAdvice(advice);
  }
  
  @Override
  public Pointcut getPointcut() {
    return pointcut;
  }

  public void setPointcut(AspectJExpressionPointcut pointcut) {
    this.pointcut = pointcut;
  }
  
}
