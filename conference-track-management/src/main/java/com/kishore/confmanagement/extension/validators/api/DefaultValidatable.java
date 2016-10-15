/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.validators.api;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.kishore.confmanagement.core.vos.sessions.ConferenceSession;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;
import com.kishore.confmanagement.extension.schedulingstrategy.vos.ConferenceSchedulerStrategyConfiguration;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
final public class DefaultValidatable implements Validatable {

  /**
   * The configuration required for validation.
   */
  private final ConferenceSchedulerStrategyConfiguration configuration;
  /**
   * The conference talk list to be evaluated.
   */
  private final List<ConferenceTalk> conferenceTalks;

  /**
   * The default constructor.
   * 
   * @param configuration The {@link ConferenceSchedulerStrategyConfiguration}
   *          instance.
   * @param conferenceTalks The {@link ConferenceTalk} list.
   */
  public DefaultValidatable(ConferenceSchedulerStrategyConfiguration
      configuration,
      List<ConferenceTalk> conferenceTalks) {
    this.configuration = configuration;
    this.conferenceTalks = conferenceTalks;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.extension.validators.api.Validatable#
   * configuration()
   */
  @Override
  public final ConferenceSchedulerStrategyConfiguration configuration() {
    return configuration;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.extension.validators.api.Validatable#
   * conferenceSessionIterator()
   */
  @Override
  public final Iterator<ConferenceSession> conferenceSessionIterator() {
    return configuration().conferenceSessionsIterator();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.extension.validators.api.Validatable#
   * conferenceTalksIterator()
   */
  @Override
  public Iterator<ConferenceTalk> conferenceTalksIterator() {
    return conferenceTalks == null ? Collections.emptyIterator()
        : conferenceTalks.iterator();
  }

}
