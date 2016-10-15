/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.common.loggers;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public abstract class BaseLogger {

  /**
   * The method to get the singleton instance of this class.
   * 
   * @return The singleton instance.
   */
  public static BaseLogger getInstance() {
    return LoggerAdapter.getInstance();
  }

  /**
   * The facade api to log the details. Underlying implementation may change.
   * 
   * @param code The message code or actual message.
   * @param params The parameters.
   */
  public abstract void logInfo(String code, Object params[]);

  /**
   * The facade api to log the details. Underlying implementation may change.
   * 
   * @param message The message.
   */
  public abstract void logFinest(String message);

  /**
   * The facade api to log the details. Underlying implementation may change.
   * 
   * @param code The message code or actual message.
   * @param throwable The {@link Throwable} instance.
   */
  public abstract void logSevere(String code, Throwable throwable);

  /**
   * The facade api to log the details. Underlying implementation may change.
   * 
   * @param code The message code or actual message.
   * @param params The parameters.
   */
  public abstract void logWarning(String code, Object params[]);

}
