/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.validators.vos;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class ValidationResult {

  /**
   * The constant for validation result.
   * 
   * @author Rajesh Kishore
   * @version 1.0
   * @since V1
   */
  public static enum ValidationOutCome {
    PASSED, FAILED
  };

  /**
   * The list of failed validators.
   */
  private List<Validator> failedValidators =
      new LinkedList<Validator>();

  /**
   * Returns the outcome of validation.
   * 
   * @return {@link ValidationOutCome}
   */
  public ValidationOutCome validationOutCome() {
    return failedValidators.isEmpty() ? ValidationOutCome.PASSED
        : ValidationOutCome.FAILED;
  }

  /**
   * The read only failed validator iterator.
   * 
   * @return The failed validator iterator.
   */
  public Iterator<Validator> failedValidatorIterator() {
    return getFailedValidators().iterator();
  }

  /**
   * Api to add the failed {@link Validator}.
   * 
   * @param validator The instance of {@link Validator}
   */
  public void addFailedValidator(Validator validator) {
    getFailedValidators().add(validator);
  }

  /**
   * The private api to return failed {@link Validator} list.
   * 
   * @return The failed {@link Validator} list.
   */
  private List<Validator> getFailedValidators() {
    return failedValidators;
  }
}
