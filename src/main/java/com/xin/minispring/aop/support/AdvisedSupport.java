package com.xin.minispring.aop.support;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.xin.minispring.aop.AdvisorChainFactory;
import com.xin.minispring.aop.DefaultAdvisorChainFactory;
import com.xin.minispring.aop.ProxyConfig;
import com.xin.minispring.aop.advisor.Advisor;
import com.xin.minispring.aop.target.TargetSource;
import com.xin.minispring.aop.target.impl.SingletonTargetSource;

/**
 * @author zx150842@126.com
 *
 */
public class AdvisedSupport extends ProxyConfig implements Advised {

  AdvisorChainFactory advisorChainFactory = new DefaultAdvisorChainFactory();
  
  private List<Advisor> advisors = new LinkedList<Advisor>();
  
  private List<Class<?>> interfaces = new ArrayList<Class<?>>();
  
  private Map<MethodCacheKey, List<Object>> methodCache = new HashMap<MethodCacheKey, List<Object>>();
  
  TargetSource targetSource;
  
  @Override
  public Class<?> getTargetClass() {
    return targetSource.getTargetClass();
  }

  @Override
  public Class<?>[] getProxiedInterfaces() {
    return interfaces.toArray(new Class<?>[interfaces.size()]);
  }

  public void addInterface(Class<?> intf) {
    if (!intf.isInterface()) {
      throw new IllegalArgumentException("[" + intf.getName() + "] is not an interface");
    }
    if (!interfaces.contains(intf)) {
      interfaces.add(intf);
    }
  }

  public void setInterfaces(Class<?>... interfaces) {
    this.interfaces.clear();
    for (Class<?> ifc : interfaces) {
      addInterface(ifc);
    }
  }

  @Override
  public boolean isInterfaceProxied(Class<?> intf) {
    // TODO Auto-generated method stub
    return false;
  }
  
  public void setTarget(Object target) {
    setTargetSource(new SingletonTargetSource(target));
  }

  @Override
  public void setTargetSource(TargetSource targetSource) {
    this.targetSource = targetSource;
  }

  @Override
  public TargetSource getTargetSource() {
    return targetSource;
  }

  @Override
  public Advisor[] getAdvisors() {
    return advisors.toArray(new Advisor[advisors.size()]);
  }

  @Override
  public void addAdvisor(Advisor advisor) {
    advisors.add(advisor);
  }

  @Override
  public void addAdvisor(int pos, Advisor advisor) {
    advisors.add(pos, advisor);
  }
  
  public List<Object> getInterceptors(Method method, Class<?> targetClass) {
    MethodCacheKey cacheKey = new MethodCacheKey(method);
    List<Object> cached = methodCache.get(cacheKey);
    if (cached == null) {
      cached = advisorChainFactory.getInterceptors(this, method, targetClass);
      methodCache.put(cacheKey, cached);    
    }
    return cached;
  }

  private static class MethodCacheKey {
    private final Method method;
    private final int hashCode;
    
    public MethodCacheKey(Method method) {
      this.method = method;
      this.hashCode = method.hashCode();
    }
    
    @Override
    public boolean equals(Object other) {
      if (other == this) {
        return true;
      }
      MethodCacheKey otherKey = (MethodCacheKey)other;
      return (this.method == otherKey.method);
    }
    
    @Override
    public int hashCode() {
      return hashCode;
    }
  }
  
}
