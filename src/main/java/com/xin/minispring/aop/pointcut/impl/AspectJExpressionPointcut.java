package com.xin.minispring.aop.pointcut.impl;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import com.xin.minispring.aop.ClassFilter;
import com.xin.minispring.aop.MethodMatcher;
import com.xin.minispring.aop.pointcut.ExpressionPointcut;
import com.xin.minispring.beans.io.ClassLoaderWrapper;

/**
 * @author zx150842@126.com
 *
 */
public class AspectJExpressionPointcut implements ExpressionPointcut, ClassFilter, MethodMatcher {

  private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();
  
  static {
    SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
    SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
    SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
    SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
    SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
    SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
    SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
    SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
    SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
  }
  
  private PointcutExpression pointcutExpression;
  private String expression;
  private ClassLoader pointcutClassLoader;
  
  @Override
  public ClassFilter getClassFilter() {
    checkReadyToMatch();
    return this;
  }

  @Override
  public MethodMatcher getMethodMatcher() {
    checkReadyToMatch();
    return this;
  }

  @Override
  public boolean matcher(Method method, Class<?> targetClass) {
    checkReadyToMatch();
    ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
    if (shadowMatch.alwaysMatches()) {
      return true;
    } else if (shadowMatch.neverMatches()) {
      return false;
    } 
    throw new IllegalArgumentException("Could not find matcher of the method " + method);
  }

  @Override
  public boolean matches(Class<?> clazz) {
    checkReadyToMatch();
    return pointcutExpression.couldMatchJoinPointsInType(clazz);
  }

  @Override
  public String getExpression() {
    checkReadyToMatch();
    return expression;
  }
  
  private void checkReadyToMatch() {
    if (pointcutExpression == null) {
      pointcutClassLoader = ClassLoaderWrapper.getDefaultClassLoader();
      PointcutParser parser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, pointcutClassLoader);
      pointcutExpression = parser.parsePointcutExpression(expression);
    }
  }
}
