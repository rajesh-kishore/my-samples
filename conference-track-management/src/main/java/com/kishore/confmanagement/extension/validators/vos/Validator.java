/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.validators.vos;

import com.kishore.confmanagement.common.loggers.BaseLogger;
import com.kishore.confmanagement.extension.validators.api.DefaultValidatable;
import com.kishore.confmanagement.extension.validators.api.Validatable;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public interface Validator {

  /**
   * The logger instance.
   */
  public final static BaseLogger LOGGER = BaseLogger.getInstance();

  /**
   * Evaluates the {@link DefaultValidatable}.
   * 
   * @param validatable {@link DefaultValidatable}
   * @return TRUE when implementation evaluated the {@link DefaultValidatable}
   *         succesfully.
   */
  public Boolean validate(Validatable validatable);
}
