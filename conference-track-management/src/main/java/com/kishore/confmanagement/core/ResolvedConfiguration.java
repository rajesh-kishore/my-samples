/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core;

import java.util.LinkedList;
import java.util.List;

import javax.swing.text.html.parser.DocumentParser;

import com.kishore.confmanagement.core.factories.DocumentParserFlyWeightFactory;
import com.kishore.confmanagement.core.parsers.DocumentProcessingParser;
import com.kishore.confmanagement.core.resolvers.ConfigurationResolver;
import com.kishore.confmanagement.core.vos.sessions.ConferenceSession;
import com.kishore.confmanagement.extension.schedulingservice.spi.ConferenceSchedulerServiceProvider;
import com.kishore.confmanagement.extension.schedulingstrategy.api.ConferenceSchedulerStrategy;
import com.kishore.confmanagement.extension.validators.vos.Validator;

/**
 * The class is resolved configuration provided by client in form of
 * {@link Configuration}. The class can be used by the core engine and hence not
 * exposed intentionally.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
final class ResolvedConfiguration {

  /**
   * The resolved document processing parser.
   */
  final private DocumentProcessingParser parser;

  /**
   * The unmodifiable list of validators provided by
   * {@link ConferenceSchedulerServiceProvider} implementor.
   */
  final private List<Validator> confManagementValidators = new
      LinkedList<Validator>();

  /**
   * The scheduling strategy provided by
   * {@link ConferenceSchedulerServiceProvider} implementor.
   */
  final private ConferenceSchedulerStrategy conferenceSchedulerStrategy;

  final private List<ConferenceSession> conferenceSessions =
      new LinkedList<ConferenceSession>();

  /**
   * Total possible duration for the day.
   * 
   * @deprecated
   */
  @Deprecated
  final private Integer totalPossibleDurationForTheDay;

  /**
   * The talk length unit such as min, hour, etc.
   */
  final private String talkLengthUnit;

  /**
   * The package level constructor to be used only for the internal working of
   * Engine.
   * 
   * @param configuration The instance to {@link Configuration}, which is a raw
   *          configuration.
   */
  ResolvedConfiguration(Configuration configuration) {
    final ConferenceSchedulerServiceProvider resolvedProvider =
        ConfigurationResolver
            .resolveConferenceSchedulerServiceProvider(configuration
                .conferenceSchedulerServiceProvider());
    this.conferenceSchedulerStrategy = resolvedProvider.schedulerStrategy();
    extractConferenceSchedulerServiceProviderInfo(configuration,
        resolvedProvider);
    this.talkLengthUnit = configuration.talkLengthUnit();
    this.totalPossibleDurationForTheDay = configuration
        .totalPossibleDurationForTheDay();
    this.parser =
        DocumentParserFlyWeightFactory.getParserInstance(configuration
            .parserType());
  }

  /**
   * Extract and sets the {@link ConferenceSchedulerServiceProvider} info.
   * 
   * @param configuration The raw {@link Configuration}
   * @param resolvedConferenceSchedulerServiceProvider
   */
  private void extractConferenceSchedulerServiceProviderInfo(
      Configuration configuration,
      ConferenceSchedulerServiceProvider
      resolvedConferenceSchedulerServiceProvider) {
    setConfManagementValidators(resolvedConferenceSchedulerServiceProvider
        .validators());

  }

  /**
   * Sets the resolved {@link Validator} list.
   * 
   * @param confManagementValidators the confManagementValidators to set
   */
  private final void setConfManagementValidators(
      List<Validator> confManagementValidators) {
    this.confManagementValidators.addAll(confManagementValidators);
  }

  /**
   * Sets the conference sessions.
   * 
   * @param conferenceSessions the conferenceSessions to set
   */
  /*
   * private final void setConferenceSessions( List<ConferenceSession>
   * conferenceSessions) { // this.conferenceSessions = conferenceSessions; }
   */

  /**
   * Returns the unmodifiable list of validators provided by
   * {@link ConferenceSchedulerServiceProvider} implementor. Any modification to
   * the list will throw Exception.
   * 
   * @return the confManagementValidators
   */
  final List<Validator> confManagementValidators() {
    return confManagementValidators;
  }

  /**
   * Returns the resolved {@link ConferenceSchedulerStrategy}.
   * 
   * @return the conferenceSchedulerStrategy
   */
  final ConferenceSchedulerStrategy conferenceSchedulerStrategy() {
    return conferenceSchedulerStrategy;
  }

  /**
   * Returns the unmodifiable list of sessions configured. Any modification to
   * the list will throw Exception.
   * 
   * @return the conferenceSessions
   */
  final List<ConferenceSession> conferenceSessions() {
    return conferenceSessions;
  }

  /**
   * @return the totalPossibleDurationForTheDay
   */
  final Integer totalPossibleDurationForTheDay() {
    return totalPossibleDurationForTheDay;
  }

  /**
   * The talk length unit.
   * 
   * @return the talkLengthUnit such as min, hour.
   */
  final String talkLengthUnit() {
    return talkLengthUnit;
  }

  /**
   * Returns boolean value based on if there are resolved validators.
   * 
   * @return TRUE when there are validators else FALSE.
   */
  final Boolean hasValidators() {
    return (confManagementValidators != null
    && confManagementValidators.isEmpty());
  }

  /**
   * Returns the resolved {@link DocumentParser}.
   * 
   * @return the parser.
   */
  final DocumentProcessingParser getParser() {
    return parser;
  }
}
