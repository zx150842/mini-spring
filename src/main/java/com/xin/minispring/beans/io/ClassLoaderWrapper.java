package com.xin.minispring.beans.io;

import java.io.InputStream;

/**
 * @author zx150842@126.com
 *
 */
public class ClassLoaderWrapper {

  private ClassLoader systemClassLoader;
  
  public ClassLoaderWrapper() {
    systemClassLoader = ClassLoader.getSystemClassLoader();
  }
  
  public InputStream getResourceAsStream(String resource) {
    return getResourceAsStream(resource, null);
  }
  
  public InputStream getResourceAsStream(String resource, ClassLoader classLoader) {
    for (ClassLoader loader : getClassLoader(classLoader)) {
      if (loader != null) {
        InputStream in = loader.getResourceAsStream(resource);
        if (in != null) {
          return in;
        }
        in = classLoader.getResourceAsStream("/" + resource);
        if (null != in) {
          return in;
        }
      }
    }
    return null;
  }

  public Class<?> classForName(String className) {
    return classForName(className, null);
  }
  
  public Class<?> classForName(String className, ClassLoader classLoader) {
    for (ClassLoader loader : getClassLoader(classLoader)) {
      if (loader != null) {
        try {
          Class<?> clazz = Class.forName(className, true, loader);
          if (clazz != null) {
            return clazz;
          }
        } catch (ClassNotFoundException e) {
        }
      }
    }
    return null;
  }
  
  public static ClassLoader getDefaultClassLoader() {
    ClassLoader classLoader = null;
    try {
      classLoader = Thread.currentThread().getContextClassLoader();
    } catch (Throwable ex) {
    }
    if (classLoader == null) {
      classLoader = ClassLoaderWrapper.class.getClassLoader();
      if (classLoader == null) {
        try {
          classLoader = ClassLoader.getSystemClassLoader();
        } catch (Throwable ex) {
        }
      }
    }
    return classLoader;
  }
  
  private ClassLoader[] getClassLoader(ClassLoader classLoader) {
    return new ClassLoader[] {
        classLoader, 
        Thread.currentThread().getContextClassLoader(),
        getClass().getClassLoader(),
        systemClassLoader
    };
  }
}
