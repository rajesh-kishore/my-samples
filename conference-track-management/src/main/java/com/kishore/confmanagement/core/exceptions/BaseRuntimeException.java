/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.exceptions;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public abstract class BaseRuntimeException extends
    RuntimeException {

  /**
   * The serialversionUID is useful when serializing and deserializing.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constructor.
   * 
   * @param e The exception.
   */
  public BaseRuntimeException(Exception e) {
    super(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Throwable#toString()
   */
  @Override
  public String toString() {
    return "BaseRuntimeException - the actual message is " + getMessage();
  }

}
