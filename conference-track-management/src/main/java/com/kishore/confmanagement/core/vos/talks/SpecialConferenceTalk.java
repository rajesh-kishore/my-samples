/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.vos.talks;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class SpecialConferenceTalk extends ConferenceTalk {

  /**
   * The serial version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The networking event.
   */
  public static final String NETWORKING_EVENT = "Networking Event";

  /**
   * The default constructor.
   * 
   * @param conferenceTitle The conference title.
   * @param talkDurationWithUnit The talk duration with unit.
   */
  public SpecialConferenceTalk(String conferenceTitle,
      String talkDurationWithUnit) {
    super(NETWORKING_EVENT, talkDurationWithUnit);
    setScheduledTime("5 pm");
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.core.vos.talks.ConferenceTalk#
   * isTalkScheduledTimeFlexible()
   */
  @Override
  public Boolean isTalkScheduledTimeFlexible() {
    return Boolean.FALSE;
  }
}
