/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.tests.vos.tracks;

import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.kishore.confmanagement.core.factories.ConferenceSessionFactory;
import com.kishore.confmanagement.core.factories.TrackFactory;
import com.kishore.confmanagement.core.vos.sessions.ConferenceSession;
import com.kishore.confmanagement.core.vos.tracks.StandardTrack;
import com.kishore.confmanagement.core.vos.tracks.Track;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public class TrackTest {

  @Test
  public void standardTrackCreation() {
    Track track = new StandardTrack(1);
    Assert.assertNotNull(track, "Error in object creation");
    Assert.assertEquals(track.getTrackId(), "1", "Id is not 1");
    Assert.assertEquals(track.getTrackName(), "Track 1", "name is not Track 1");
  }

  @Test
  public void standardTrackCreationByFactory() {
    Track track = null;
    track = TrackFactory.createTrack(TrackFactory.TrackTypes.STANDARD_TRACK, 2);
    Assert.assertNotNull(track,
        "Error in object creation");
    Assert.assertTrue(track instanceof StandardTrack,
        "Error in object creation");
    Assert.assertEquals(track.getTrackId(), "2", "Id is not 2");
    Assert.assertEquals(track.getTrackName(), "Track 2", "name is not Track 2");
  }

  @Test
  public void defaultTrackNotEmptySessionsTest() {
    Track track = null;
    track = TrackFactory.createTrack(TrackFactory.TrackTypes.STANDARD_TRACK, 2);
    Assert.assertEquals(track.getTrackId(), "2", "Id is not 2");
    Assert.assertTrue(track.conferenceSessionsIterator().hasNext(),
        "by default session should not be empty");
  }

  @Test
  public void defaultTrackShouldHaveConfiguredSessionsTest() {
    Track track = null;
    track = TrackFactory.createTrack(TrackFactory.TrackTypes.STANDARD_TRACK, 2);
    Assert.assertEquals(track.getTrackId(), "2", "Id is not 2");

    Assert.assertTrue(track.conferenceSessionsIterator().hasNext(),
        "by default session should not be empty");
    List<ConferenceSession> conferenceSessions =
        ConferenceSessionFactory.getSingletonInstance()
            .createAllConfiguredSession();
    for (ConferenceSession session : conferenceSessions) {
      Boolean validateEachSession = Boolean.FALSE;
      for (Iterator<ConferenceSession> sessionThruTrack = track
          .conferenceSessionsIterator(); sessionThruTrack.hasNext();) {
        if (sessionThruTrack.next().sessionName().equals(session.sessionName())) {
          validateEachSession = Boolean.TRUE;
          break;
        }
      }
      Assert.assertTrue(validateEachSession,
          "Session not found thru track creation.");
    }
  }
}
