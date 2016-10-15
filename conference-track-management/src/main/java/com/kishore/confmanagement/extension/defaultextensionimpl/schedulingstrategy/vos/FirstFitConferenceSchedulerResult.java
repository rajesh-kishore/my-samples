/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.defaultextensionimpl.schedulingstrategy.vos;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.kishore.confmanagement.core.vos.sessions.ConferenceSession;
import com.kishore.confmanagement.core.vos.tracks.Track;
import com.kishore.confmanagement.extension.schedulingstrategy.vos.ConferenceSchedulerResult;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class FirstFitConferenceSchedulerResult extends
    ConferenceSchedulerResult {

  public static final ConferenceSchedulerResult FAILED_RESULT =
      new FirstFitConferenceSchedulerResult(SchedulerOutcome.ERROR,
          Collections.emptyList());

  /**
   * The only constructor.
   * 
   * @param outcome The scheduler outcome {@link SchedulerOutcome}
   * @param tracks The list of {@link Track}
   */
  public FirstFitConferenceSchedulerResult(SchedulerOutcome outcome,
      List<Track> tracks) {
    super(outcome, tracks);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.schedulingstrategy.vos.
   * ConferenceSchedulerResult#outcome()
   */
  @Override
  public SchedulerOutcome outcome() {
    return schedulerOutcome;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.schedulingstrategy.vos.
   * ConferenceSchedulerResult#result()
   */
  @Override
  public List<Track> result() {
    return tracks == null ? Collections.emptyList() : tracks;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.core.vos.scheduler.
   * ConferenceSchedulerResult#updateSessionWithDefaultTalk(java.util.List)
   */
  @Override
  protected void updateSessionWithDefaultTalk(List<Track> tracks) {
    for (ListIterator<Track> iteratorTrack =
        tracks.listIterator(); iteratorTrack
        .hasNext();) {
      final Track currentTrack = iteratorTrack.next();
      Iterator<ConferenceSession> conferenceSessionIterator =
          currentTrack.conferenceSessionsIterator();
      while (conferenceSessionIterator.hasNext()) {
        final ConferenceSession conferenceSession =
            conferenceSessionIterator.next();
        conferenceSession.addDefaultMandatoryTalk();
      }
    }
  }
}
