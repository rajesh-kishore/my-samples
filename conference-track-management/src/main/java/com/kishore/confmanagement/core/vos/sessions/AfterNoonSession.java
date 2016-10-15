/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.vos.sessions;

import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;
import com.kishore.confmanagement.core.vos.talks.SpecialConferenceTalk;

/**
 * Its specific implementation of {@link ConferenceSession}.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class AfterNoonSession extends ConferenceSession {

  /**
   * The default constructor.
   */
  public AfterNoonSession() {
    super("1:00 pm", "5:00 pm");
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
    return "Afternoon Session";
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
    ConferenceTalk conferenceTalk = new SpecialConferenceTalk(
        SpecialConferenceTalk.NETWORKING_EVENT,"100min");
    return conferenceTalk;
  }

}
