/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.vos.tracks;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.kishore.confmanagement.core.factories.ConferenceSessionFactory;
import com.kishore.confmanagement.core.generators.TrackIdNameGenerator;
import com.kishore.confmanagement.core.vos.sessions.ConferenceSession;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public abstract class Track {

  /**
   * The track ID.
   */
  private final String trackId;

  /**
   * The track name.
   */
  private final String trackName;

  /**
   * The configured {@link ConferenceSession} in the system.
   */
  private List<ConferenceSession> conferenceSessions =
      new LinkedList<ConferenceSession>();

  protected Track(TrackIdNameGenerator generator) {
    this.trackId = generator.generateTrackId();
    this.trackName = generator.generateTrackName();
    createConfiguredConferenceSessions();
  }

  /*
   * private Track(Integer trackId, String trackName) {
   * System.out.println(trackId); setTrackId(trackId); setTrackName(trackName);
   * createConfiguredConferenceSessions(); }
   */

  /**
   * Resolves and creates the configured the conference session in the system.
   */
  private void createConfiguredConferenceSessions() {
    ConferenceSessionFactory conferenceSessionFactory =
        ConferenceSessionFactory.getSingletonInstance();
    conferenceSessions.addAll(conferenceSessionFactory
        .createAllConfiguredSession());
  }

  /**
   * @return the trackId
   */
  public final String getTrackId() {
    return trackId;
  }

  /**
   * @return the trackName
   */
  public final String getTrackName() {
    return trackName;
  }

  /**
   * @return the conferenceSessions
   */
  public final Iterator<ConferenceSession> conferenceSessionsIterator() {
    return conferenceSessions.iterator();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder stringBuilder =
        new StringBuilder(200);
    stringBuilder.append("[Track name : " + getTrackName());
    stringBuilder.append(", Conference Sessions : [");
    for (Iterator<ConferenceSession> conferenceSessionIterator =
        conferenceSessions.iterator(); conferenceSessionIterator
        .hasNext();) {
      stringBuilder.append(conferenceSessionIterator.next().toString());
      if (conferenceSessionIterator.hasNext()) {
        stringBuilder.append(",");
      }
    }
    stringBuilder.append("]");
    return stringBuilder.toString();
  }

  /**
   * The foreign helper method to accommodate conference talk. By default it
   * does not add.
   * 
   * @param targetConferenceTalk The conference talk.
   * @return TRUE if the conference talk could be added else FALSE.
   */
  public Boolean tryAcommodatingConferenceTalk(
      ConferenceTalk targetConferenceTalk) {
    return Boolean.FALSE;
  }
}
