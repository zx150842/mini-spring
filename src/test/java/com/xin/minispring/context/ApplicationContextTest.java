package com.xin.minispring.context;

import org.junit.Test;

import com.xin.minispring.bean.HelloWorldService;
import com.xin.minispring.bean.HelloWorldServiceImpl;
import com.xin.minispring.context.ApplicationContext;
import com.xin.minispring.context.impl.ClassPathXmlApplicationContext;

/**
 * ioc和aop的测试类
 * 
 * @author zx150842@126.com
 *
 */
public class ApplicationContextTest {

  /**
   * 测试ioc
   * 
   * @throws Exception
   */
  @Test
  public void beanTest() throws Exception {
    System.out.println("\nbean Test starting---");
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("minispring.xml");
    HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
    helloWorldService.helloWorld();
    System.out.println("bean Test ending---\n");
  }
  
  /**
   * 测试aop中的aop:before和aop:afterReturning
   * 
   * @throws Exception
   */
  @Test
  public void aopTest() throws Exception {
    System.out.println("\naop Test starting---");
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("minispring-aop.xml");
    HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
    helloWorldService.unInterceptMethod();
    helloWorldService.helloWorld();
    System.out.println("aop Test ending---\n");
  }
  
  /**
   * 测试aop中的aop:afterThrowing
   * 
   * @throws Exception
   */
  @Test
  public void aopThrowsTest() throws Exception {
    System.out.println("\naopThrows Test starting---");
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("minispring-aop.xml");
    HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
    try {
      helloWorldService.exceptionMethod();
    } catch (Throwable ex) {
      System.out.println(ex.getMessage());
    }
    System.out.println("aopThrows Test ending---\n");
  }
  
  /**
   * 测试aop的aop:around和当目标方法有多个切面匹配时指定切面执行顺序
   * 
   * @throws Exception
   */
  @Test
  public void adviceTest() throws Exception {
    System.out.println("\nadvice Test starting---");
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("minispring-advice.xml");
    HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
    helloWorldService.helloWorld();
    System.out.println("advice Test ending---\n");
  }
  
  /**
   * 测试在非拦截方法中调用拦截方法使用AopContext获取当前代理类
   * 
   * @throws Exception
   */
  @Test
  public void aopExposeProxyTest() throws Exception {
    System.out.println("\naopExpose Test starting---");
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("minispring-aop.xml");
    HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
    helloWorldService.unInterceptMethodWithInterceptMethodInternal();
    System.out.println("aopExpose Test ending---\n");
  }
  
  /**
   * 测试非接口方法调用, proxyTargetClass需要设为true
   * 
   * @throws Exception
   */
  @Test
  public void aopUnInterfaceMethodTest() throws Exception {
    System.out.println("\naopUnInterface Test starting---");
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("minispring-aop.xml");
    HelloWorldServiceImpl helloWorldService = (HelloWorldServiceImpl) applicationContext.getBean("helloWorldService");
    helloWorldService.unInterfaceMethod();
    System.out.println("aopUnInterface Test ending---\n");
  }
}
