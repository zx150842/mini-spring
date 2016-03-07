package com.xin.minispring.beans.factory.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;

import com.xin.minispring.beans.BeanDefinition;
import com.xin.minispring.beans.BeanReference;
import com.xin.minispring.beans.factory.AbstractBeanFactory;
import com.xin.minispring.beans.factory.BeanFactoryAware;

/**
 * @author zx150842@126.com
 *
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

  @Override
  protected void applyBeanProperties(BeanDefinition beanDefinition) throws Exception {
    Object bean = beanDefinition.getBean();
    if (bean instanceof BeanFactoryAware) {
      ((BeanFactoryAware) bean).setBeanFactory(this);
    }
    Properties properties = beanDefinition.getProperties();
    if (properties != null) {
      for (Map.Entry<Object, Object> entry : properties.entrySet()) {
        String fieldName = (String)entry.getKey();
        Object value = entry.getValue();
        if (value instanceof BeanReference) {
          String refBeanName = ((BeanReference)value).getName();
          value = getBean(refBeanName);
        } 
        for (Class<?> clazz = bean.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
          try {
            Method method = clazz.getDeclaredMethod("set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1), value.getClass());
            method.setAccessible(true);
            value = wrapValue(value, method.getParameterTypes()[0]);
            method.invoke(bean, value);
            break;
          } catch (NoSuchMethodException e) {
            Field field;
            try {
              field = clazz.getDeclaredField(fieldName);
              field.setAccessible(true);
              value = wrapValue(value, field.getType());
              field.set(bean, value);
              break;
            } catch (Exception ex) {
              if (clazz.getSuperclass() == null) {
                throw ex;
              }
            } 
          }
        }
      }
    }
  }
  
  protected Object wrapValue(Object value, Class<?> clazz) {
    if (boolean.class.isAssignableFrom(clazz) || Boolean.class.isAssignableFrom(clazz)) {
      return Boolean.valueOf((String)value);
    } else if (Byte.class.isAssignableFrom(clazz)) {
      return Byte.valueOf((String)value);
    } else if (Short.class.isAssignableFrom(clazz)) {
      return Short.valueOf((String)value);
    } else if (Integer.class.isAssignableFrom(clazz)) {
      return Integer.valueOf((String)value);
    } else if (Long.class.isAssignableFrom(clazz)) {
      return Long.valueOf((String)value);
    } else if (Float.class.isAssignableFrom(clazz)) {
      return Float.valueOf((String)value);
    } else if (Double.class.isAssignableFrom(clazz)) {
      return Double.valueOf((String)value);
    } else {
      return value;
    }
  } 
}
