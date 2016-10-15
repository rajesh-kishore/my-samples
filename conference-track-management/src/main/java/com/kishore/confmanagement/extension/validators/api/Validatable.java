/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.validators.api;

import java.util.Iterator;

import com.kishore.confmanagement.core.vos.sessions.ConferenceSession;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;
import com.kishore.confmanagement.extension.schedulingstrategy.vos.ConferenceSchedulerStrategyConfiguration;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public interface Validatable {

  /**
   * @return the configuration
   */
  public abstract ConferenceSchedulerStrategyConfiguration configuration();

  /**
   * The foreign method to return configured sessions.
   * 
   * @return The iterator representing configured session.
   */
  public abstract Iterator<ConferenceSession> conferenceSessionIterator();

  /**
   * The read only iterator for {@link ConferenceTalk}.
   * 
   * @return The read only iterator
   */
  public abstract Iterator<ConferenceTalk> conferenceTalksIterator();

}