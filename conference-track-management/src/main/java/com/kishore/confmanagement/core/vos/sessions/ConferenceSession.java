/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.vos.sessions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.kishore.confmanagement.common.loggers.BaseLogger;
import com.kishore.confmanagement.core.factories.ConferenceSessionFactory;
import com.kishore.confmanagement.core.factories.DurationResolverFlyWeightFactory;
import com.kishore.confmanagement.core.factories.DurationResolverFlyWeightFactory.DURATION_UNIT;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;

/**
 * The class encapsulates all conference session related information.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public abstract class ConferenceSession {

  /**
   * The start time of session.
   */
  final private String startTime;

  /**
   * The end time of session.
   */
  final private String endTime;

  /**
   * The conference talks.
   */
  private List<ConferenceTalk> conferenceTalks =
      new LinkedList<ConferenceTalk>();

  /**
   * The left over time for the session.
   */
  private Integer leftOverTime;

  /**
   * The only constructor which expects startTime and endTime for each session.
   * Callers are expected to call {@link ConferenceSessionFactory} to create
   * concrete status.
   * 
   * @param startTime the start time in format format 10:00 am or 10:00 am. or
   *          10:00 AM or 10:00 AM.
   * @param endTime the end time in format format 10:00 am or 10:00 am. or 10:00
   *          AM or 10:00 AM.
   */
  protected ConferenceSession(String startTime, String endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
    leftOverTime =
        DurationResolverFlyWeightFactory.getDurationResolver(
            DURATION_UNIT.MINUTES).resolveTimeDurationInUnit(startTime(),
            endTime());
  }

  /**
   * Returns the total duration.
   * 
   * @return the total duration.
   */
  public int totalDuration() {
    return DurationResolverFlyWeightFactory.getDurationResolver(
        DURATION_UNIT.MINUTES).
        resolveTimeDurationInUnit(startTime(), endTime());
  }

  /**
   * Returns the session name.
   * 
   * @return The session name.
   */
  public abstract String sessionName();

  /**
   * Returns the start time.
   * 
   * @return the start time.
   */
  public String startTime() {
    return startTime;
  }

  /**
   * Returns the end time.
   * 
   * @return The end time.
   */
  public String endTime() {
    return endTime;
  }

  /**
   * Returns the default {@link ConferenceTalk} configured for the session.
   * 
   * @return The conference talk.
   */
  public ConferenceTalk defaultConferenceTalk() {
    return ConferenceTalk.NO_DEFAULT_CONFERENCE_TALK;
  }

  /**
   * Returns the left over time.
   * 
   * @return the left over time.
   */
  protected Integer leftOverTime() {
    return leftOverTime;
  }

  /**
   * Checks if the {@link ConferenceTalk} is eligible to fit in current session.
   * 
   * @param conferenceTalk The instance of {@link ConferenceTalk}
   * @return TRUE if the talk in context is eligible to fit in the current
   *         session else FALSE.
   */
  public Boolean isTalkApplicable(ConferenceTalk conferenceTalk) {
    Boolean status = conferenceTalk.talkDuration() <= totalDuration();
    // TODO: change it to StringBuilder
    BaseLogger.getInstance().logInfo(
        "eligibility criteria of " + conferenceTalk.conferenceTalkTitle()
            + " for session " + sessionName() + " is " + status
            + ", the talk duration " + conferenceTalk.talkDuration()
            + ", session  duration " + totalDuration(), null);
    return status;
  }

  /**
   * Tries to fit the {@link ConferenceTalk} in correct session.
   * 
   * @param conferenceTalk The conference talk.
   * @return TRUE when successfully added else FALSE.
   */
  public Boolean tryAcumulatingConferenceTalk(ConferenceTalk conferenceTalk) {
    if (conferenceTalk.talkDuration() > leftOverTime()) {
      return Boolean.FALSE;
    }

    /*
     * if (defaultConferenceTalk() != null) { if
     * (defaultConferenceTalk().talkDuration() + conferenceTalk.talkDuration() >
     * leftOverTime()) {
     * 
     * } }
     */

    conferenceTalks.add(conferenceTalk);
    conferenceTalk.setScheduledTime(DurationResolverFlyWeightFactory
        .getDurationResolver(DURATION_UNIT.MINUTES).resolveTimeinHourFormat(
            endTime(), leftOverTime()));
    setLeftOverTime((leftOverTime())
        - conferenceTalk.talkDuration());
    return Boolean.TRUE;
  }

  /**
   * Sets the leftOver time.
   * 
   * @param leftOverTime the leftOverTime to set
   */
  private final void setLeftOverTime(Integer leftOverTime) {
    this.leftOverTime = leftOverTime;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder stringBuilder =
        new StringBuilder(100);
    stringBuilder.append("[Session name : " + sessionName());
    stringBuilder.append(", Conference Talks : [");
    for (Iterator<ConferenceTalk> conferenceTalkIterator =
        conferenceTalks.iterator(); conferenceTalkIterator.hasNext();) {
      stringBuilder.append(conferenceTalkIterator.next());
      if (conferenceTalkIterator.hasNext()) {
        stringBuilder.append(",");
      }
    }
    stringBuilder.append("], ");
    stringBuilder.append("start time : " + startTime() + ", end time : "
        + endTime() + "]");
    return stringBuilder.toString();
  }

  /**
   * @return the conferenceTalks
   */
  private final List<ConferenceTalk> getConferenceTalks() {
    return conferenceTalks;
  }

  /**
   * Adds the default talk configured for the session.
   */
  public void addDefaultMandatoryTalk() {
    if (defaultConferenceTalk() != ConferenceTalk.NO_DEFAULT_CONFERENCE_TALK) {
      getConferenceTalks().add(defaultConferenceTalk());
    }
  }
}
