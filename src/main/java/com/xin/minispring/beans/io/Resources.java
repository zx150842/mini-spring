package com.xin.minispring.beans.io;

import java.io.InputStream;

/**
 * @author zx150842@126.com
 *
 */
public class Resources {

  private static ClassLoaderWrapper classLoaderWrapper = new ClassLoaderWrapper();
  
  public static InputStream getResourceAsStream(String resource) {
    InputStream in = classLoaderWrapper.getResourceAsStream(resource);
    if (in != null) {
      return in;
    }
    throw new IllegalArgumentException("Cannot get inputstream from location: " + resource);
  }
  
  public static InputStream getResourceAsStream(String resource, ClassLoader classLoader) {
    InputStream in = classLoaderWrapper.getResourceAsStream(resource, classLoader);
    if (in != null) {
      return in;
    }
    throw new IllegalArgumentException("Cannot get inputstream from location: " + resource);
  }
  
  public static Class<?> classForName(String className) {
    Class<?> clazz = classLoaderWrapper.classForName(className);
    if (clazz != null) {
      return clazz;
    }
    throw new IllegalArgumentException("Cannot get class which class name: " + className);
  }
  
  public static Class<?> classForName(String className, ClassLoader classLoader) {
    Class<?> clazz = classLoaderWrapper.classForName(className, classLoader);
    if (clazz != null) {
      return clazz;
    }
    throw new IllegalArgumentException("Cannot get class which class name: " + className);
  }
}
