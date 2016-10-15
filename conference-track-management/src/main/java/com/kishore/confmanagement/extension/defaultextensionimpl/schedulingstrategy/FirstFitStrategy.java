/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.defaultextensionimpl.schedulingstrategy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;
import com.kishore.confmanagement.core.vos.tracks.Track;
import com.kishore.confmanagement.extension.defaultextensionimpl.schedulingstrategy.vos.FirstFitConferenceSchedulerResult;
import com.kishore.confmanagement.extension.schedulingstrategy.api.ConferenceSchedulerStrategy;
import com.kishore.confmanagement.extension.schedulingstrategy.vos.ConferenceSchedulerResult;
import com.kishore.confmanagement.extension.schedulingstrategy.vos.ConferenceSchedulerStrategyConfiguration;
import com.kishore.confmanagement.extension.schedulingstrategy.vos.ConferenceSchedulerResult.SchedulerOutcome;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class FirstFitStrategy implements ConferenceSchedulerStrategy {

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.schedulingstrategy.api.
   * ConferenceSchedulerStrategy
   * #scheduleConference(com.kishore.confmanagement.core.Configurator,
   * java.util.List)
   */
  @Override
  public ConferenceSchedulerResult scheduleConference(
      ConferenceSchedulerStrategyConfiguration conferenceConfiguration,
      List<ConferenceTalk> conferenceTalks) {

    ConferenceSchedulerResult conferenceSchedulerResult = null;
    try {

      final List<Track> tracks = new LinkedList<Track>();
      int sequence = 1;
      // We need to initialize at least the first track first.
      tracks.add(FirstFitStrategyHelper.createRequiredTrack(sequence));

      for (ConferenceTalk eachConferenceTalk : conferenceTalks) {
        Boolean talkAdded = Boolean.FALSE;

        talkAdded = FirstFitStrategyHelper
            .tryUpdatingPossibleConferenceSessionInPossibleTrack(tracks,
                eachConferenceTalk);

        // if above logic could not find the correct match for talk, then create
        // a new track and insert the talk in appropriate session.
        if (!talkAdded) {
          sequence++;
          Track nextTrack =
              FirstFitStrategyHelper.createRequiredTrack(sequence);
          tracks.add(nextTrack);
          FirstFitStrategyHelper.findBestFitInCurrentTrack(nextTrack,
              eachConferenceTalk);
        }
      }
      conferenceSchedulerResult =
          new FirstFitConferenceSchedulerResult(SchedulerOutcome.EXECUTED,
              Collections.unmodifiableList(tracks));
    } catch (Exception e) {
      // TODO:log the result
      conferenceSchedulerResult =
          new FirstFitConferenceSchedulerResult(SchedulerOutcome.ERROR,
              Collections.emptyList());
    }
    return conferenceSchedulerResult;
  }

}
