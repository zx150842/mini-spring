package com.xin.minispring.aop.advisor;

import org.aopalliance.aop.Advice;

import com.xin.minispring.aop.Ordered;

/**
 * @author zx150842@126.com
 *
 */
public abstract class AbstractPointcutAdvisor implements PointcutAdvisor {

  private Integer order;
  private Advice advice;
  
  public void setOrder(Integer order) {
    this.order = order;
  }
  
  public void setAdvice(Advice advice) {
    this.advice = advice;
  }

  @Override
  public int getOrder() {
    if (order != null) {
      return order;
    }
    return Ordered.LOWEST_PRECEDENCE;
  }
  
  @Override
  public Advice getAdvice() {
    return advice;
  }
}
