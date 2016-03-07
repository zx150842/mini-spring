package com.xin.minispring.beans;

/**
 * @author zx150842@126.com
 *
 */
public class BeanReference {

  private String name;
  private String Object;

  public BeanReference(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getObject() {
    return Object;
  }

  public void setObject(String object) {
    Object = object;
  }
}
