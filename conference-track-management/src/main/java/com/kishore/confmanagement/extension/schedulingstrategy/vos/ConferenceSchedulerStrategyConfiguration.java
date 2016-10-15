/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.schedulingstrategy.vos;

import java.util.Iterator;
import java.util.List;

import com.kishore.confmanagement.core.factories.ConferenceSessionFactory;
import com.kishore.confmanagement.core.vos.sessions.ConferenceSession;
import com.kishore.confmanagement.extension.schedulingstrategy.api.ConferenceSchedulerStrategy;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class ConferenceSchedulerStrategyConfiguration {

  /**
   * The configured conference session.
   */
  final private List<ConferenceSession> conferenceSessions;

  /**
   * The talk length unit.
   */
  final private String talkLengthUnit;

  /**
   * The total duration for the day.
   */
  @Deprecated
  final private Integer totalPossibleDurationForTheDay;

  /**
   * The private constructor.
   * 
   * @param conferenceSessions The session list.
   * @param totalPossibleDurationForTheDay total duration for the day.
   * @param talkLengthUnit The talk length unit such as min, hour, etc.
   */
  private ConferenceSchedulerStrategyConfiguration(
      List<ConferenceSession> conferenceSessions,
      Integer totalPossibleDurationForTheDay,
      String talkLengthUnit) {
    this.conferenceSessions = conferenceSessions;
    this.talkLengthUnit = talkLengthUnit;
    this.totalPossibleDurationForTheDay = totalPossibleDurationForTheDay;
  }

  /**
   * The static factory method to create the configuration required by
   * {@link ConferenceSchedulerStrategy}.
   * 
   * @param totalPossibleDurationForTheDay The total possible duration for the
   *          day.
   * @param talkLengthUnit the talk length unit.
   * @return The instance of {@link ConferenceSchedulerStrategyConfiguration}
   */
  public static ConferenceSchedulerStrategyConfiguration
      createConferenceConfiguration(
          Integer totalPossibleDurationForTheDay,
          String talkLengthUnit) {
    return new ConferenceSchedulerStrategyConfiguration(
        ConferenceSessionFactory.getSingletonInstance()
            .createAllConfiguredSession(),
        totalPossibleDurationForTheDay,talkLengthUnit);
  }

  /**
   * Returns the talk length unit.
   * 
   * @return the talkLengthUnit
   */
  public final String talkLengthUnit() {
    return talkLengthUnit;
  }

  /**
   * Returns the possible duration for the day.
   * 
   * @return the totalPossibleDurationForTheDay
   */
  public final Integer totalPossibleDurationForTheDay() {
    return totalPossibleDurationForTheDay;
  }

  /**
   * @return the conferenceSessions
   */
  public final Iterator<ConferenceSession> conferenceSessionsIterator() {
    return conferenceSessions.iterator();
  }

}
