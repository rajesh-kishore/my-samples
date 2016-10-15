/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.vos.sessions;

import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;

/**
 * Its specific implementation of {@link ConferenceSession}.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class MorningSession extends ConferenceSession {

  /**
   * The default constructor.
   */
  public MorningSession() {
    super("9:00 am", "12:00 pm");
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.kishore.confmanagement.schedulingstrategy.vos.ConferenceSession
   * #sessionName()
   */
  @Override
  public String sessionName() {
    return "Morning Session";
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.kishore.confmanagement.schedulingstrategy.vos.ConferenceSession
   * #defaultConferenceTalk()
   */
  @Override
  public ConferenceTalk defaultConferenceTalk() {
    return super.defaultConferenceTalk();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.kishore.confmanagement.schedulingstrategy.vos.ConferenceSession
   * #addConferenceTalk(com.kishore.confmanagement.core.vos.ConferenceTalk)
   */
  @Override
  public Boolean tryAcumulatingConferenceTalk(ConferenceTalk conferenceTalk) {
    return super.tryAcumulatingConferenceTalk(conferenceTalk);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return super.toString();
  }
}
