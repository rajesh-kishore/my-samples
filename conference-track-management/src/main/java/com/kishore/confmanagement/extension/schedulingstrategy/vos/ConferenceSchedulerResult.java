/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.schedulingstrategy.vos;

import java.util.List;

import com.kishore.confmanagement.core.vos.tracks.Track;

/**
 * The class encapsulates the scheduled result.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public abstract class ConferenceSchedulerResult {

  /**
   * The outcome.
   */
  protected final SchedulerOutcome schedulerOutcome;

  /**
   * The list of tracks obtained out of result.
   */
  protected final List<Track> tracks;

  /*
   * public final static ConferenceSchedulerResult NOTEXECUTED = new
   * ConferenceSchedulerResult();
   */

  /**
   * The scheduler outcome.
   * 
   * @author Rajesh Kishore
   * @version 1.0
   * @since V1
   */
  public enum SchedulerOutcome {
    EXECUTED, ERROR
  };

  /**
   * The protected constructor.
   * 
   * @param outcome The {@link SchedulerOutcome}.
   * @param tracks The list of {@link Track}
   */
  protected ConferenceSchedulerResult(SchedulerOutcome outcome,
      List<Track> tracks) {
    this.schedulerOutcome = outcome;
    this.tracks = tracks;
    if (outcome == SchedulerOutcome.EXECUTED) {
      updateSessionWithDefaultTalk(tracks);
    }
  }

  /**
   * Updates the result, be default it does not do anything. The subclass has to
   * update this.
   * 
   * @param tracks The track list.
   */
  protected void updateSessionWithDefaultTalk(List<Track> tracks) {
    // does nothing by default
  }

  /**
   * The result outcome {@link #schedulerOutcome}.
   * 
   * @return The outcome {@link SchedulerOutcome}
   */
  public abstract SchedulerOutcome outcome();

  /**
   * The result list in form of {@link Track}.
   * 
   * @return The list of tracks.
   */
  public abstract List<Track> result();

}
