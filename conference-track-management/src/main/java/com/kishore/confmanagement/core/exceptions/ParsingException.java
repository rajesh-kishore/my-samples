/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.exceptions;

/**
 * The parsing exception class.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public class ParsingException extends BaseRuntimeException {

  /**
   * The default constructor.
   * 
   * @param e The exception raised.
   */
  public ParsingException(Exception e) {
    super(e);
  }

  /**
   * The serial version.
   */
  private static final long serialVersionUID = 1L;

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.core.exceptions.
   * BaseRuntimeException#toString ()
   */
  @Override
  public String toString() {
    return "ConferenceTalkParsingException - the actual message is "
        + getMessage();
  }

}
