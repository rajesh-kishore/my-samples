/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core;

import com.kishore.confmanagement.core.factories.DocumentParserFlyWeightFactory;
import com.kishore.confmanagement.core.factories.DocumentParserFlyWeightFactory.PARSERTYPE;
import com.kishore.confmanagement.extension.schedulingservice.spi.ConferenceSchedulerServiceProvider;

/**
 * The class contains the raw configuration provided by client. 
 * <code>
 * Configuration configuration =
        ConfigurationBuilder.defaultConfigurationBuilder().
        buildConfiguration();
 * or
 * new ConfigurationBuilder(
            ConfigurationBuilder.
            DEFAULT_CONFERENCE_SCHEDULER_SERVICE_PROVIDER).
            buildConfiguration();
  </code>
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class Configuration {

  /**
   * The parser type for processing the document.
   */
  final private DocumentParserFlyWeightFactory.PARSERTYPE parserType;

  /**
   * The {@link ConferenceSchedulerServiceProvider} implementor class name.Its
   * not resolved at this point of time.
   */
  final private String conferenceSchedulerServiceProvider;

  /**
   * The possible duration for the day.
   * 
   * @deprecated
   */

  @Deprecated
  final private Integer totalPossibleDurationForTheDay;

  /**
   * The talk length unit , the default is min.
   */
  final private String talkLengthUnit;

  Configuration(ConfigurationBuilder configuratorBuilder) {
    this.conferenceSchedulerServiceProvider =
        configuratorBuilder.conferenceSchedulerServiceProvider();
    this.talkLengthUnit = configuratorBuilder.talkLengthUnit();
    this.totalPossibleDurationForTheDay = configuratorBuilder
        .totalPossibleDurationForTheDay();
    this.parserType = configuratorBuilder.parserType();
  }

  /**
   * The method creates the {@link ConferenceManager} instance given
   * configuration passed by the client.
   * 
   * @return The instance of {@link ConferenceManager}
   */
  public ConferenceManager createConferenceManager() {
    return new ConferenceManager(this);
  }

  /**
   * The conference scheduler service provider name.
   * 
   * @return The implementor class name for
   *         {@link ConferenceSchedulerServiceProvider}.
   */
  public final String conferenceSchedulerServiceProvider() {
    return conferenceSchedulerServiceProvider;
  }

  /**
   * Returns the total possible duration for the day.
   * 
   * @return The total possible duration for the day
   * @deprecated
   */
  @Deprecated
  public Integer totalPossibleDurationForTheDay() {
    return totalPossibleDurationForTheDay;
  }

  /**
   * Returns the talk length unit, such as min, hour.
   * 
   * @return The talk length unit.
   */
  public String talkLengthUnit() {
    return talkLengthUnit;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder(50);
    builder.append("[Provider class name :")
        .append(conferenceSchedulerServiceProvider())
        .append(",talkLengthUnit is ").append(talkLengthUnit())
        .append(", parser type is " + parserType() + "]");
    return builder.toString();
  }

  /**
   * Retruns the parser type.
   * 
   * @return The parser type.
   */
  public PARSERTYPE parserType() {
    return parserType;
  }
}
