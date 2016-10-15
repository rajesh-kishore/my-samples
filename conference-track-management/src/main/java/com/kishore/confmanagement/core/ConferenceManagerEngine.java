/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core;

import java.io.InputStream;
import java.util.List;

import com.kishore.confmanagement.common.loggers.BaseLogger;
import com.kishore.confmanagement.core.vos.ConferenceResult;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;
import com.kishore.confmanagement.extension.schedulingstrategy.vos.ConferenceSchedulerResult;
import com.kishore.confmanagement.extension.schedulingstrategy.vos.ConferenceSchedulerStrategyConfiguration;
import com.kishore.confmanagement.extension.validators.api.DefaultValidatable;
import com.kishore.confmanagement.extension.validators.api.Validatable;
import com.kishore.confmanagement.extension.validators.vos.ValidationResult;
import com.kishore.confmanagement.extension.validators.vos.ValidationResult.ValidationOutCome;

/**
 * The class is for internal working of {@link ConferenceManager}.This is solely
 * for internal purpose and cannot be access by the client.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
final class ConferenceManagerEngine {

  /**
   * The logger instance.
   */
  final private static BaseLogger LOGGER = BaseLogger.getInstance();

  /**
   * The resolved configuration.
   */
  private ResolvedConfiguration resolvedConfiguration = null;

  private ConferenceManagerEngine(ResolvedConfiguration configuration) {
    this.resolvedConfiguration = configuration;
  }

  /**
   * Returns the resolved configuration.
   * 
   * @param rawConfiguration The instance of {@link Configuration}
   * @return
   */
  private static ResolvedConfiguration resolveConfiguration(
      Configuration rawConfiguration) {
    ResolvedConfiguration resolvedConfiguration =
        new ResolvedConfiguration(rawConfiguration);
    return resolvedConfiguration;
  }

  /**
   * The static factory method for creating the instance of current class.
   * 
   * @param rawConfiguration The instance of {@link Configuration}.
   * @return The instance of current class.
   */
  public static ConferenceManagerEngine
      createConferenceManagerEngineByConfiguration(
          Configuration rawConfiguration) {
    LOGGER.logFinest("The raw configuration is " + rawConfiguration);
    ResolvedConfiguration resolvedConfiguration =
        resolveConfiguration(rawConfiguration);
    LOGGER.logFinest("The resolved configuration is " + resolvedConfiguration);
    return new ConferenceManagerEngine(resolvedConfiguration);
  }

  /**
   * Submits the task to scheduler.
   * 
   * @param conferenceTalkLst
   */
  public ConferenceResult submit(List<ConferenceTalk> conferenceTalkLst) {

    final ConferenceSchedulerStrategyConfiguration conferenceConfiguration =
        prepareConferenceConfiguration();

    final Validatable validatable =
        new DefaultValidatable(conferenceConfiguration,conferenceTalkLst);

    final ValidationResult validationResult =
        ValidatorHelper.doValidation(validatable, resolvedConfiguration
            .confManagementValidators().iterator());

    LOGGER.logInfo("validation outcome  " +
        validationResult.validationOutCome().toString(), null);

    if (validationResult.validationOutCome() == ValidationOutCome.FAILED) {
      BaseLogger.getInstance().logInfo("validation failed ", null);
      final ConferenceResult conferenceResult =
          ConferenceResult
              .createConferenceResultByValidationFailed(validationResult);
      return conferenceResult;
    }

    final ConferenceSchedulerResult conferenceSchedulerResult =
        resolvedConfiguration.conferenceSchedulerStrategy()
            .scheduleConference(
                conferenceConfiguration, conferenceTalkLst);

    LOGGER.logInfo("The execution is completed.", null);

    return ConferenceResult.createConferenceResultByConferenceSchedulerResult(
        validationResult, conferenceSchedulerResult);
  }

  /**
   * Submits the task to scheduler.
   * 
   * @param stream The input stream.
   */
  public ConferenceResult submit(InputStream stream) {

    LOGGER
        .logFinest("submitting stream to engine, the document"
            + " could be of any source ");
    List<ConferenceTalk> resolvedConferenceTalks = null;
    resolvedConferenceTalks =
        resolvedConfiguration.getParser().parseListOfTalks(stream);
    LOGGER.logFinest(" objects resolved successfully "
        + resolvedConferenceTalks);
    return submit(resolvedConferenceTalks);
  }

  /**
   * Prepares the configuration required by service provider.
   * 
   * @return The instance of {@link ConferenceSchedulerStrategyConfiguration}
   */
  private ConferenceSchedulerStrategyConfiguration
      prepareConferenceConfiguration() {
    return ConferenceSchedulerStrategyConfiguration
        .createConferenceConfiguration(
            resolvedConfiguration.totalPossibleDurationForTheDay(),
            resolvedConfiguration.talkLengthUnit());
  }

}
