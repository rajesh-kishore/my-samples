/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.defaultextensionimpl.schedulingstrategy;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.kishore.confmanagement.core.vos.sessions.ConferenceSession;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;
import com.kishore.confmanagement.core.vos.tracks.Track;
import com.kishore.confmanagement.extension.defaultextensionimpl.schedulingstrategy.vos.tracks.FirstFitTrack;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
final class FirstFitStrategyHelper {

  /**
   * The method tries to find the right place for given conference talk.
   * 
   * @param tracks The track list identified so far
   * @param targetConferenceTalk The conference talk which needs to find its
   *          right place.
   * @param talkAdded
   * @return
   */
  static Boolean tryUpdatingPossibleConferenceSessionInPossibleTrack(
      List<Track> tracks, ConferenceTalk targetConferenceTalk) {
    Boolean talkAdded = Boolean.FALSE;
    for (ListIterator<Track> iteratorTrack =
        tracks.listIterator(); iteratorTrack
        .hasNext();) {
      final Track currentTrack = iteratorTrack.next();
      talkAdded =
          currentTrack.tryAcommodatingConferenceTalk(targetConferenceTalk);
      if (talkAdded) {
        break;
      }
    }
    return talkAdded;
  }

  static Track createRequiredTrack(Integer sequence) {
    /*
     * TrackFactory.createTrack(TrackFactory.TrackTypes.STANDARD_TRACK,
     * sequence);
     */
    return new FirstFitTrack(sequence);
  }

  /**
   * The utility method adds the conference talk in current track which is fresh
   * track in appropriate session, this method is guaranteed to add the talk.
   * 
   * @param newlyCreatedTrack The current track in which the conference talk has
   *          to be added, its a newly created track.
   * @param currenConferenceTalk
   */
  static void findBestFitInCurrentTrack(Track newlyCreatedTrack,
      ConferenceTalk currenConferenceTalk) {
    final Iterator<ConferenceSession> conferenceSessionIterator =
        newlyCreatedTrack.conferenceSessionsIterator();
    while (conferenceSessionIterator.hasNext()) {
      final ConferenceSession conferenceSession =
          conferenceSessionIterator.next();
      if (conferenceSession.
          tryAcumulatingConferenceTalk(currenConferenceTalk)) {
        break;
      }
    }
  }
}
