/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core;

import com.kishore.confmanagement.core.factories.DocumentParserFlyWeightFactory;
import com.kishore.confmanagement.core.factories.DocumentParserFlyWeightFactory.PARSERTYPE;
import com.kishore.confmanagement.extension.schedulingservice.spi.ConferenceSchedulerServiceProvider;

/**
 * The class is the builder class for creating configuration object. This is
 * primary consumer facing class. <code>
 * ConfigurationBuilder configurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();
 * or
 * new ConfigurationBuilder(
            ConfigurationBuilder.DEFAULT_CONFERENCE_SCHEDULER_SERVICE_PROVIDER);
            
 * </code>
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class ConfigurationBuilder {

  /**
   * The default talk length unit.
   */
  private static final String DEFAULT_TALK_LENGTH_UNIT = "min";

  /**
   * The default parser type.
   */
  private static final DocumentParserFlyWeightFactory.PARSERTYPE PARSER_TYPE =
      DocumentParserFlyWeightFactory.PARSERTYPE.XML;
  /**
   * The parser type for processing the document.
   */
  private DocumentParserFlyWeightFactory.PARSERTYPE parserType =
      PARSER_TYPE;

  /**
   * The default provider class name for
   * {@link ConferenceSchedulerServiceProvider}
   */
  public static final String DEFAULT_CONFERENCE_SCHEDULER_SERVICE_PROVIDER =
      "com.kishore.confmanagement.extension.defaultextensionimpl.provider"
          + ".DefaultConferenceSchedulerProvider";

  /**
   * The default configuration builder.
   */
  private static final ConfigurationBuilder DEFAULT_CONFIGURATION_BUILDER;

  static {
    DEFAULT_CONFIGURATION_BUILDER =
        new ConfigurationBuilder(DEFAULT_CONFERENCE_SCHEDULER_SERVICE_PROVIDER);
    DEFAULT_CONFIGURATION_BUILDER.setTalkLengthUnit(DEFAULT_TALK_LENGTH_UNIT);
    DEFAULT_CONFIGURATION_BUILDER.setDefaultParserType(PARSER_TYPE);
  }

  /**
   * The implementor provider class name for
   * {@link ConferenceSchedulerServiceProvider}.
   */
  final private String conferenceSchedulerServiceProvider;

  /**
   * The possible duration for the day.
   * 
   * @deprecated
   */
  @Deprecated
  private Integer totalPossibleDurationForTheDay;

  /**
   * The talk length unit such as min, hour.
   */
  private String talkLengthUnit;

  /**
   * The default and only constructor visible to consumer, which expects the
   * implementor provider class name for
   * {@link ConferenceSchedulerServiceProvider} , consumer can use the
   * Configuration with default object using {@link #buildConfiguration()}.
   * 
   * @param conferenceSchedulerServiceProviderClassName The class name for
   *          provider.
   */
  public ConfigurationBuilder(String
      conferenceSchedulerServiceProviderClassName) {
    this.conferenceSchedulerServiceProvider =
        conferenceSchedulerServiceProviderClassName;
  }

  /**
   * Sets the default parser type. Default is set to XML parser type.
   * 
   * @param defaultParserType The parser type.
   * @return The {@link ConfigurationBuilder} instance.
   */
  public final ConfigurationBuilder setDefaultParserType(
      PARSERTYPE defaultParserType) {
    this.parserType = defaultParserType;
    return this;
  }

  /**
   * @return the conferenceSchedulerStrategy
   */
  final String conferenceSchedulerServiceProvider() {
    return conferenceSchedulerServiceProvider;
  }

  /**
   * @return the totalPossibleDurationForTheDay
   */
  @Deprecated
  public final Integer totalPossibleDurationForTheDay() {
    return totalPossibleDurationForTheDay;
  }

  /**
   * The method returns the talk length unit configured, default is min.
   * 
   * @return the talkLengthUnit
   */
  public final String talkLengthUnit() {
    return talkLengthUnit;
  }

  /**
   * The talk length unit name to be set.
   * 
   * @param talkLengthUnit the talkLengthUnit to set
   * @return the {@link ConfigurationBuilder}
   */
  public final ConfigurationBuilder setTalkLengthUnit(String talkLengthUnit) {
    this.talkLengthUnit = talkLengthUnit;
    return this;
  }

  /**
   * Returns the default configuration instance.
   * 
   * @return The default {@link Configuration} instance.
   */
  public Configuration defaultConfiguration() {
    return new Configuration(DEFAULT_CONFIGURATION_BUILDER);
  }

  /**
   * Builds the configuration.
   * 
   * @return The {@link Configuration} instance.
   */
  public Configuration buildConfiguration() {
    return new Configuration(this);
  }

  /**
   * The default configuration builder.
   * 
   * @return the defaultConfigurationBuilder
   */
  public static final ConfigurationBuilder defaultConfigurationBuilder() {
    return DEFAULT_CONFIGURATION_BUILDER;
  }

  /**
   * Returns the parser type configured, defaults to XML.
   * 
   * @return the parserType
   */
  public final DocumentParserFlyWeightFactory.PARSERTYPE parserType() {
    return parserType;
  }
}
