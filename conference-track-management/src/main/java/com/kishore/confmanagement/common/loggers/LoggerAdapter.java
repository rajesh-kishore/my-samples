/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.common.loggers;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class LoggerAdapter extends BaseLogger {

  /**
   * The logger name.
   */
  private static final String LOGGER_NAME = "com.kishore.confmanagement";

  /**
   * The resource bundle base name required for translation.
   */
  private static final String RESOURCE_BUNDLE_NAME =
      "logging";

  /**
   * The JUL logger as of now.
   */
  private static final Logger logger = Logger.getLogger(
      LOGGER_NAME, RESOURCE_BUNDLE_NAME);

  /**
   * The private constructor.
   */
  private LoggerAdapter() {

  }

  /**
   * The helper class to create the singleton instance.
   * 
   * @author Rajesh Kishore
   * @version 1.0
   * @since V1
   */
  private static class LoggerAdapterSingletonCreator {
    private static LoggerAdapter loggerAdapter = new LoggerAdapter();
  }

  /**
   * Returns the singleton instance of this class.
   * 
   * @return The singleton instance of this class.
   */
  public static LoggerAdapter getInstance() {
    return LoggerAdapterSingletonCreator.loggerAdapter;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.kishore.confmanagement.common.loggers.BaseLogger#logInfo(java.
   * lang.String, java.lang.Object[])
   */
  @Override
  public void logInfo(String code, Object[] params) {
    getLogger().log(Level.INFO, code, params);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.kishore.confmanagement.common.loggers.BaseLogger#logFinest(java
   * .lang.String)
   */
  @Override
  public void logFinest(String message) {
    getLogger().log(Level.FINEST, message);

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.kishore.confmanagement.common.loggers.BaseLogger#logSevere(java
   * .lang.String, java.lang.Object[])
   */
  @Override
  public void logSevere(String code, Throwable throwable) {
    getLogger().log(Level.SEVERE, code, throwable);

  }

  /**
   * @return the logger
   */
  public static final Logger getLogger() {
    return logger;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.kishore.confmanagement.common.loggers.BaseLogger#logWarning(java
   * .lang.String, java.lang.Object[])
   */
  @Override
  public void logWarning(String code, Object[] params) {
    getLogger().log(Level.WARNING, code, params);
  }

}
