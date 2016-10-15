/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.vos.talks;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.kishore.confmanagement.common.constants.Constants;
import com.kishore.confmanagement.core.factories.DurationResolverFlyWeightFactory;
import com.kishore.confmanagement.core.factories.DurationResolverFlyWeightFactory.DURATION_UNIT;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlJavaTypeAdapter( ConferenceTalkAdapter.class )
public class ConferenceTalk implements Serializable {

  /**
   * The serial version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The dummy conference talk with no actual talk.
   */
  public static final ConferenceTalk NO_DEFAULT_CONFERENCE_TALK =
      new ConferenceTalk("No Conference Talk", "0min");

  /**
   * The immutable conference title.
   */
  // @XmlElement
  final private String conferenceTalkTitle;

  /**
   * /** The immutable talk duration.
   */
  final private int talkDuration;

  /**
   * /** The immutable duration unit.
   */
  final private String talkDurationUnit;

  /**
   * Holds time and unit information.
   */
  final private String talkDurationWithUnit;

  /**
   * The actual time talk started.
   */
  private String scheduledStartTime;

  /**
   * the only default constructor.
   * 
   * @param conferenceTitle The conference title.
   * @param talkDurationWithUnit The talk duration unit.
   */
  public ConferenceTalk(String conferenceTitle, String talkDurationWithUnit) {
    this.conferenceTalkTitle = conferenceTitle;
    this.talkDurationWithUnit = talkDurationWithUnit;
    final Map<String, Object> resolvedTalkDuration =
        DurationResolverFlyWeightFactory.getDurationResolver(
            DURATION_UNIT.MINUTES).talkDurationResolver(talkDurationWithUnit);
    this.talkDuration =
        (Integer) resolvedTalkDuration.get(Constants.RESOLVEDDURATION);
    this.talkDurationUnit =
        (String) resolvedTalkDuration.get(Constants.RESOLVEDUNIT);
  }

  /**
   * The talk duration.
   * 
   * @return The talk duration.
   */
  public int talkDuration() {
    return talkDuration;
  }

  /**
   * The actual time when talk can be started.
   * 
   * @param lastestStartTime The actual scheduled time for this talk.
   */
  public void setScheduledTime(String lastestStartTime) {
    this.scheduledStartTime = lastestStartTime;
  }

  /**
   * @return the conferenceTitle
   */
  public final String conferenceTalkTitle() {
    return conferenceTalkTitle;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    final StringBuilder stringBuilder =
        new StringBuilder(30);
    stringBuilder.append("[Title : " + conferenceTalkTitle());
    stringBuilder.append(", Duration : " + talkDuration());
    stringBuilder.append(", Scheduled Time : " + scheduledStartTime());
    stringBuilder.append("]");
    return stringBuilder.toString();
  }

  /**
   * @param startTime the startTime to set
   */
  /*
   * protected void setStartTime(String startTime) { this.scheduledStartTime =
   * startTime; }
   */

  /**
   * @return the latestStartTime
   */
  public final String scheduledStartTime() {
    return scheduledStartTime;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof ConferenceTalk)) {
      return false;
    }
    return (((ConferenceTalk) obj).conferenceTalkTitle().equalsIgnoreCase(
        conferenceTalkTitle()));
  }

  /**
   * Return the talk duration unit.
   * 
   * @return the talkDurationUnit.
   */
  public final String talkDurationUnit() {
    return talkDurationUnit;
  }

  /**
   * Determines if the talk scheduled time is flexible.
   * 
   * @return TRUE when talk scheduled time flexible.
   */
  public Boolean isTalkScheduledTimeFlexible() {
    return Boolean.TRUE;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return conferenceTalkTitle().hashCode();
  }

}
