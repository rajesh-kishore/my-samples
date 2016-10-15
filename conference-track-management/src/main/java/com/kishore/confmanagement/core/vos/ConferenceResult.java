/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.vos;

import com.kishore.confmanagement.extension.schedulingstrategy.vos.ConferenceSchedulerResult;
import com.kishore.confmanagement.extension.schedulingstrategy.vos.ConferenceSchedulerResult.SchedulerOutcome;
import com.kishore.confmanagement.extension.validators.vos.ValidationResult;
import com.kishore.confmanagement.extension.validators.vos.ValidationResult.ValidationOutCome;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class ConferenceResult {

  private ValidationResult validationResult;

  private ConferenceSchedulerResult conferenceSchedulerResult;

  private ConferenceResult(ValidationResult validationResult,
      ConferenceSchedulerResult conferenceSchedulerResult) {
    this.validationResult = validationResult;
    this.conferenceSchedulerResult = conferenceSchedulerResult;
  }

  /**
   * The factory method to create the instance of {@link ConferenceResult}
   * 
   * @param validationResult The {@link ValidationResult}
   * @return The {@link ConferenceResult} instance.
   */
  public static ConferenceResult createConferenceResultByValidationFailed(
      ValidationResult validationResult) {
    return new ConferenceResult(validationResult,null);
  }

  /**
   * The factory method to create overall result.
   * 
   * @param validationResult The {@link ValidationResult}
   * @param conferenceSchedulerResult The {@link ConferenceSchedulerResult}
   * @return The {@link ConferenceResult}
   */
  public static ConferenceResult
      createConferenceResultByConferenceSchedulerResult(
          ValidationResult validationResult,
          ConferenceSchedulerResult conferenceSchedulerResult) {
    return new ConferenceResult(validationResult,conferenceSchedulerResult);
  }

  /**
   * @return the validationResult
   */
  public final ValidationResult validationResult() {
    return validationResult;
  }

  /**
   * @return the conferenceSchedulerResult
   */
  public final ConferenceSchedulerResult conferenceSchedulerResult() {
    return conferenceSchedulerResult;
  }

  /**
   * The foreign method defined in this class for easier access.
   * 
   * @return The validation outcome defined as {@link ValidationOutCome}
   */
  public ValidationOutCome validationOutCome() {
    return validationResult().validationOutCome();
  }

  /**
   * The foreign method defined in this class for easier access of
   * ConferenceSchedulerResult outcome.
   * 
   * @return The validation outcome defined as {@link SchedulerOutcome}
   */
  public SchedulerOutcome schedulerOutcome() {
    return conferenceSchedulerResult().outcome();
  }

}
