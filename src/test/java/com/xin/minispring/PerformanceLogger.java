package com.xin.minispring;

/**
 * 通知方法类
 * 
 * @author zhangxin
 *
 */
public class PerformanceLogger {

  public void welcome() {
    System.out.println("Welcome!");
  }
  
  public void goodbye() {
    System.out.println("GoodBye!");
  }
  
  public void afterThrowing(Exception e) {
    System.out.println("performance e");
  }
}
