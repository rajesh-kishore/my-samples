/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.vos.sessions;

import com.kishore.confmanagement.common.loggers.BaseLogger;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;

/**
 * Its specific implementation of {@link ConferenceSession}.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class LunchSession extends ConferenceSession {

  /**
   * The default constructor.
   */
  public LunchSession() {
    super("12:00 pm", "1:00 pm");
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
    return "Lunch Session";
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
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return super.toString();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.kishore.confmanagement.schedulingstrategy.vos.ConferenceSession
   * #tryAcumulatingConferenceTalk
   * (com.kishore.confmanagement.core.vos.ConferenceTalk)
   */
  @Override
  public Boolean tryAcumulatingConferenceTalk(ConferenceTalk conferenceTalk) {
    return Boolean.FALSE;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.kishore.confmanagement.schedulingstrategy.vos.ConferenceSession
   * #isTalkApplicable(com.kishore.confmanagement.core.vos.ConferenceTalk)
   */
  @Override
  public Boolean isTalkApplicable(ConferenceTalk conferenceTalk) {
    BaseLogger.getInstance().logInfo(
        "eligibility criteria of " + conferenceTalk.conferenceTalkTitle()
            + " for session " + sessionName() + " is " + Boolean.FALSE
            + ", the talk duration " + conferenceTalk.talkDuration()
            + ", session  duration " + totalDuration(), null);

    return Boolean.FALSE;
  }
}
