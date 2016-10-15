/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.schedulingstrategy.api;

import java.util.List;

import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;
import com.kishore.confmanagement.extension.schedulingstrategy.vos.ConferenceSchedulerResult;
import com.kishore.confmanagement.extension.schedulingstrategy.vos.ConferenceSchedulerStrategyConfiguration;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public interface ConferenceSchedulerStrategy {

  /**
   * Schedules the conference.
   * 
   * @param conferenceConfiguration The configuration required by scheduler.
   * @param conferenceTalks The conference talks list.
   * @return The {@link ConferenceSchedulerResult}
   */
  public ConferenceSchedulerResult scheduleConference(
      ConferenceSchedulerStrategyConfiguration conferenceConfiguration,
      List<ConferenceTalk> conferenceTalks);
}
