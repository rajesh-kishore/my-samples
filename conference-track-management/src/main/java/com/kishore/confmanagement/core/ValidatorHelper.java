/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core;

import java.util.Iterator;

import com.kishore.confmanagement.common.loggers.BaseLogger;
import com.kishore.confmanagement.extension.validators.api.DefaultValidatable;
import com.kishore.confmanagement.extension.validators.api.Validatable;
import com.kishore.confmanagement.extension.validators.vos.ValidationResult;
import com.kishore.confmanagement.extension.validators.vos.Validator;

/**
 * Its a validator helper class used for doing validation.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
final class ValidatorHelper {

  /**
   * The logger instance.
   */
  private static final BaseLogger LOGGER = BaseLogger.getInstance();

  /**
   * Does the validation.
   * 
   * @param validatable {@link DefaultValidatable} instance.
   * @param validatorIterator The validator iterator.
   * @return {@link ValidationResult} instance.
   */
  static ValidationResult doValidation(Validatable validatable,
      Iterator<Validator> validatorIterator) {
    final ValidationResult validationResult = new ValidationResult();
    if (validatorIterator == null) {
      return validationResult;
    }
    while (validatorIterator.hasNext()) {
      final Validator validator = validatorIterator.next();
      if (!validator.validate(validatable)) {
        LOGGER.logInfo(
            "Validator " + validator + "has failed", null);
        validationResult.addFailedValidator(validator);
        // continue to find all the validators which may fail.
      }
    }
    return validationResult;
  }
}
