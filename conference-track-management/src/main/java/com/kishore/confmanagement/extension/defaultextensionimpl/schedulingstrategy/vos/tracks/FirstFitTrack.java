/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.defaultextensionimpl.schedulingstrategy.vos.tracks;

import java.util.Iterator;

import com.kishore.confmanagement.core.vos.sessions.ConferenceSession;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;
import com.kishore.confmanagement.core.vos.tracks.StandardTrack;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class FirstFitTrack extends StandardTrack {

  /**
   * The default constructor.
   * 
   * @param sequence The sequence.
   */
  public FirstFitTrack(Integer sequence) {
    super(sequence);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.core.vos.tracks.Track#
   * tryAcommodatingConferenceTalk
   * (com.kishore.confmanagement.core.vos.talks.ConferenceTalk)
   */
  @Override
  public Boolean tryAcommodatingConferenceTalk(
      ConferenceTalk targetConferenceTalk) {

    Boolean talkAdded = Boolean.FALSE;
    Iterator<ConferenceSession> conferenceSessionIterator =
        conferenceSessionsIterator();
    while (conferenceSessionIterator.hasNext()) {
      ConferenceSession conferenceSession =
          conferenceSessionIterator.next();
      if (conferenceSession
          .tryAcumulatingConferenceTalk(targetConferenceTalk)) {
        talkAdded = Boolean.TRUE;
        break;
      }
    }
    return talkAdded;

  }

}
