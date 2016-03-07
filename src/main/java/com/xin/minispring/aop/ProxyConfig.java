package com.xin.minispring.aop;

/**
 * @author zx150842@126.com
 *
 */
public class ProxyConfig {

  private boolean proxyTargetClass = false;
  private boolean exposeProxy = false;
  // 暂不支持修改以下配置
  private boolean optimize = false;
  private boolean opaque = false;
  private boolean frozen = false;

  public boolean isProxyTargetClass() {
    return proxyTargetClass;
  }

  public void setProxyTargetClass(boolean proxyTargetClass) {
    this.proxyTargetClass = proxyTargetClass;
  }

  public boolean isOptimize() {
    return optimize;
  }

  public void setOptimize(boolean optimize) {
    this.optimize = optimize;
  }

  public boolean isOpaque() {
    return opaque;
  }

  public void setOpaque(boolean opaque) {
    this.opaque = opaque;
  }

  public boolean isExposeProxy() {
    return exposeProxy;
  }

  public void setExposeProxy(boolean exposeProxy) {
    this.exposeProxy = exposeProxy;
  }
  
  public boolean getExposeProxy() {
    return exposeProxy;
  }

  public boolean isFrozen() {
    return frozen;
  }

  public void setFrozen(boolean frozen) {
    this.frozen = frozen;
  }
  
  public void copyFrom(ProxyConfig other) {
    this.proxyTargetClass = other.proxyTargetClass;
    this.optimize = other.optimize;
    this.exposeProxy = other.exposeProxy;
    this.frozen = other.frozen;
    this.opaque = other.opaque;
  }

}
