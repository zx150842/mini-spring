package com.xin.minispring.beans.exceptions;

/**
 * @author zx150842@126.com
 *
 */
public class IllegalBeanException extends RuntimeException {
  private static final long serialVersionUID = -3145859622817450777L;

  public IllegalBeanException() {
    super();
  }
  
  public IllegalBeanException(String message) {
    super(message);
  }
  
  public IllegalBeanException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public IllegalBeanException(Throwable cause) {
    super(cause);
  }
}
