/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.tests.defaultextensionimpl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.kishore.confmanagement.common.loggers.BaseLogger;
import com.kishore.confmanagement.core.ConferenceManager;
import com.kishore.confmanagement.core.Configuration;
import com.kishore.confmanagement.core.ConfigurationBuilder;
import com.kishore.confmanagement.core.factories.ConferenceSessionFactory;
import com.kishore.confmanagement.core.factories.DocumentParserFlyWeightFactory.PARSERTYPE;
import com.kishore.confmanagement.core.vos.ConferenceResult;
import com.kishore.confmanagement.core.vos.sessions.ConferenceSession;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;
import com.kishore.confmanagement.core.vos.tracks.Track;
import com.kishore.confmanagement.extension.schedulingstrategy.vos.ConferenceSchedulerResult.SchedulerOutcome;
import com.kishore.confmanagement.extension.validators.vos.ValidationResult.ValidationOutCome;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class FirstSchedulingStrategyTest {

  final BaseLogger instance = BaseLogger.getInstance();

  @Test
  public void conferenceSchedulingStrategyWithFirstInputTest() {

    ConfigurationBuilder configurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();

    Configuration configuration = configurationBuilder.buildConfiguration();
    ConferenceManager conferenceManager =
        configuration.createConferenceManager();

    List<ConferenceTalk> conferenceTalks = new LinkedList<ConferenceTalk>();
    conferenceTalks.add(new ConferenceTalk(
        "Writing Fast Tests Against Enterprise Rails", "60min"));
    conferenceTalks.add(new ConferenceTalk("Overdoing it in Python", "45min"));
    conferenceTalks.add(new ConferenceTalk("Lua for the Masses", "30min"));
    conferenceTalks.add(new ConferenceTalk(
        "Ruby Errors from Mismatched Gem Versions", "45min"));
    conferenceTalks.add(new ConferenceTalk("Common Ruby Errors", "45min"));
    conferenceTalks.add(new ConferenceTalk("Rails for Python Developers",
        "lightning"));
    conferenceTalks.add(new ConferenceTalk("Communicating Over Distance",
        "60min"));
    conferenceTalks.add(new ConferenceTalk("Accounting-Driven Development",
        "45min"));
    conferenceTalks.add(new ConferenceTalk("Woah", "30min"));
    conferenceTalks.add(new ConferenceTalk("Sit Down and Write", "30min"));
    conferenceTalks
        .add(new ConferenceTalk("Pair Programming vs Noise", "45min"));
    conferenceTalks.add(new ConferenceTalk("Rails Magic", "60min"));
    conferenceTalks.add(new ConferenceTalk(
        "Ruby on Rails: Why We Should Move On", "60min"));
    conferenceTalks.add(new ConferenceTalk("Clojure Ate Scala (on my project)",
        "45min"));
    conferenceTalks.add(new ConferenceTalk(
        "Programming in the Boondocks of Seattle", "30min"));
    conferenceTalks.add(new ConferenceTalk(
        "Ruby vs. Clojure for Back-End Development", "30min"));
    conferenceTalks.add(new ConferenceTalk(
        "Ruby on Rails Legacy App Maintenance", "60min"));
    conferenceTalks.add(new ConferenceTalk(
        "Writing Fast Tests Against Enterprise Rails", "60min"));

    conferenceTalks
        .add(new ConferenceTalk("A World Without HackerNews", "30min"));
    conferenceTalks.add(new ConferenceTalk("User Interface CSS in Rails Apps",
        "30min"));

    ConferenceResult conferenceResult =
        conferenceManager.scheduleConference(conferenceTalks);

    Assert.assertNotNull(conferenceResult, "The result is null.");

    Assert.assertEquals(conferenceResult.validationOutCome(),
        ValidationOutCome.PASSED, "The validation failed.");

    Assert.assertEquals(conferenceResult.schedulerOutcome(),
        SchedulerOutcome.EXECUTED,
        " The strategy algorithm could not be executed successfully.");

    Assert.assertEquals(conferenceResult.conferenceSchedulerResult().result()
        .size(), 3);

    int currentCounter = 0;
    String trackToString[] =
        {
            "[Track name : Track 1, Conference Sessions : [[Session name : Morning Session, Conference Talks : [[Title : Writing Fast Tests Against Enterprise Rails, Duration : 60, Scheduled Time : 09:00 AM],[Title : Overdoing it in Python, Duration : 45, Scheduled Time : 10:00 AM],[Title : Lua for the Masses, Duration : 30, Scheduled Time : 10:45 AM],[Title : Ruby Errors from Mismatched Gem Versions, Duration : 45, Scheduled Time : 11:15 AM]], start time : 9:00 am, end time : 12:00 pm],[Session name : Lunch Session, Conference Talks : [], start time : 12:00 pm, end time : 1:00 pm],[Session name : Afternoon Session, Conference Talks : [[Title : Common Ruby Errors, Duration : 45, Scheduled Time : 01:00 PM],[Title : Rails for Python Developers, Duration : 5, Scheduled Time : 01:45 PM],[Title : Communicating Over Distance, Duration : 60, Scheduled Time : 01:50 PM],[Title : Accounting-Driven Development, Duration : 45, Scheduled Time : 02:50 PM],[Title : Woah, Duration : 30, Scheduled Time : 03:35 PM],[Title : Sit Down and Write, Duration : 30, Scheduled Time : 04:05 PM],[Title : Networking Event, Duration : 100, Scheduled Time : 5 pm]], start time : 1:00 pm, end time : 5:00 pm]]",
            "[Track name : Track 2, Conference Sessions : [[Session name : Morning Session, Conference Talks : [[Title : Pair Programming vs Noise, Duration : 45, Scheduled Time : 09:00 AM],[Title : Rails Magic, Duration : 60, Scheduled Time : 09:45 AM],[Title : Ruby on Rails: Why We Should Move On, Duration : 60, Scheduled Time : 10:45 AM]], start time : 9:00 am, end time : 12:00 pm],[Session name : Lunch Session, Conference Talks : [], start time : 12:00 pm, end time : 1:00 pm],[Session name : Afternoon Session, Conference Talks : [[Title : Clojure Ate Scala (on my project), Duration : 45, Scheduled Time : 01:00 PM],[Title : Programming in the Boondocks of Seattle, Duration : 30, Scheduled Time : 01:45 PM],[Title : Ruby vs. Clojure for Back-End Development, Duration : 30, Scheduled Time : 02:15 PM],[Title : Ruby on Rails Legacy App Maintenance, Duration : 60, Scheduled Time : 02:45 PM],[Title : Writing Fast Tests Against Enterprise Rails, Duration : 60, Scheduled Time : 03:45 PM],[Title : Networking Event, Duration : 100, Scheduled Time : 5 pm]], start time : 1:00 pm, end time : 5:00 pm]]",
            "[Track name : Track 3, Conference Sessions : [[Session name : Morning Session, Conference Talks : [[Title : A World Without HackerNews, Duration : 30, Scheduled Time : 09:00 AM],[Title : User Interface CSS in Rails Apps, Duration : 30, Scheduled Time : 09:30 AM]], start time : 9:00 am, end time : 12:00 pm],[Session name : Lunch Session, Conference Talks : [], start time : 12:00 pm, end time : 1:00 pm],[Session name : Afternoon Session, Conference Talks : [[Title : Networking Event, Duration : 100, Scheduled Time : 5 pm]], start time : 1:00 pm, end time : 5:00 pm]]"
        };
    for (Track eachTrack : conferenceResult.conferenceSchedulerResult()
        .result()) {
      currentCounter++;
      Assert.assertEquals(eachTrack.getTrackName(), "Track " + currentCounter);
      validateConfiguredSession(eachTrack);
      // System.out.println("track " + eachTrack);

      instance.logInfo("eachTrack " + eachTrack, null);
      Assert.assertEquals(trackToString[currentCounter - 1], eachTrack
          .toString().trim());
    }

  }

  @Test
  public void conferenceSchedulingStrategyWithSecondInputTest() {

    ConfigurationBuilder configurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();

    Configuration configuration = configurationBuilder.buildConfiguration();
    ConferenceManager conferenceManager =
        configuration.createConferenceManager();

    List<ConferenceTalk> conferenceTalks = new LinkedList<ConferenceTalk>();
    conferenceTalks.add(new ConferenceTalk(
        "Writing Fast Tests Against Enterprise Rails", "60min"));
    conferenceTalks.add(new ConferenceTalk("Overdoing it in Python", "45min"));
    conferenceTalks.add(new ConferenceTalk("Lua for the Masses", "30min"));
    conferenceTalks.add(new ConferenceTalk(
        "Ruby Errors from Mismatched Gem Versions", "45min"));

    ConferenceResult conferenceResult =
        conferenceManager.scheduleConference(conferenceTalks);

    Assert.assertNotNull(conferenceResult, "The result is null.");

    Assert.assertEquals(conferenceResult.validationOutCome(),
        ValidationOutCome.PASSED, "The validation failed.");

    Assert.assertEquals(conferenceResult.schedulerOutcome(),
        SchedulerOutcome.EXECUTED,
        " The strategy algorithm could not be executed successfully.");

    Assert.assertEquals(conferenceResult.conferenceSchedulerResult().result()
        .size(), 1);

    int currentCounter = 0;
    String trackToString[] =
        {
            "[Track name : Track 1, Conference Sessions : [[Session name : Morning Session, Conference Talks : [[Title : Writing Fast Tests Against Enterprise Rails, Duration : 60, Scheduled Time : 09:00 AM],[Title : Overdoing it in Python, Duration : 45, Scheduled Time : 10:00 AM],[Title : Lua for the Masses, Duration : 30, Scheduled Time : 10:45 AM],[Title : Ruby Errors from Mismatched Gem Versions, Duration : 45, Scheduled Time : 11:15 AM]], start time : 9:00 am, end time : 12:00 pm],[Session name : Lunch Session, Conference Talks : [], start time : 12:00 pm, end time : 1:00 pm],[Session name : Afternoon Session, Conference Talks : [[Title : Networking Event, Duration : 100, Scheduled Time : 5 pm]], start time : 1:00 pm, end time : 5:00 pm]]",
        };
    for (Track eachTrack : conferenceResult.conferenceSchedulerResult()
        .result()) {
      currentCounter++;
      Assert.assertEquals(eachTrack.getTrackName(), "Track " + currentCounter);
      validateConfiguredSession(eachTrack);
      // System.out.println("track " + eachTrack);
      Assert.assertEquals(trackToString[currentCounter - 1], eachTrack
          .toString().trim());
    }

  }

  @Test
  public void conferenceSchedulingStrategyWithThirdInputTest() {

    ConfigurationBuilder configurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();

    Configuration configuration = configurationBuilder.buildConfiguration();
    ConferenceManager conferenceManager =
        configuration.createConferenceManager();

    List<ConferenceTalk> conferenceTalks = new LinkedList<ConferenceTalk>();
    conferenceTalks.add(new ConferenceTalk(
        "Writing Fast Tests Against Enterprise Rails", "60min"));
    conferenceTalks.add(new ConferenceTalk("Overdoing it in Python", "45min"));
    conferenceTalks.add(new ConferenceTalk("Lua for the Masses", "30min"));
    conferenceTalks.add(new ConferenceTalk(
        "Ruby Errors from Mismatched Gem Versions", "45min"));
    conferenceTalks.add(new ConferenceTalk("Common Ruby Errors", "45min"));
    conferenceTalks.add(new ConferenceTalk("Rails for Python Developers",
        "lightning"));
    conferenceTalks.add(new ConferenceTalk("Communicating Over Distance",
        "60min"));

    ConferenceResult conferenceResult =
        conferenceManager.scheduleConference(conferenceTalks);

    Assert.assertNotNull(conferenceResult, "The result is null.");

    Assert.assertEquals(conferenceResult.validationOutCome(),
        ValidationOutCome.PASSED, "The validation failed.");

    Assert.assertEquals(conferenceResult.schedulerOutcome(),
        SchedulerOutcome.EXECUTED,
        " The strategy algorithm could not be executed successfully.");

    Assert.assertEquals(conferenceResult.conferenceSchedulerResult().result()
        .size(), 1);

    int currentCounter = 0;
    String trackToString[] =
        {
            "[Track name : Track 1, Conference Sessions : [[Session name : Morning Session, Conference Talks : [[Title : Writing Fast Tests Against Enterprise Rails, Duration : 60, Scheduled Time : 09:00 AM],[Title : Overdoing it in Python, Duration : 45, Scheduled Time : 10:00 AM],[Title : Lua for the Masses, Duration : 30, Scheduled Time : 10:45 AM],[Title : Ruby Errors from Mismatched Gem Versions, Duration : 45, Scheduled Time : 11:15 AM]], start time : 9:00 am, end time : 12:00 pm],[Session name : Lunch Session, Conference Talks : [], start time : 12:00 pm, end time : 1:00 pm],[Session name : Afternoon Session, Conference Talks : [[Title : Common Ruby Errors, Duration : 45, Scheduled Time : 01:00 PM],[Title : Rails for Python Developers, Duration : 5, Scheduled Time : 01:45 PM],[Title : Communicating Over Distance, Duration : 60, Scheduled Time : 01:50 PM],[Title : Networking Event, Duration : 100, Scheduled Time : 5 pm]], start time : 1:00 pm, end time : 5:00 pm]]"
        };
    for (Track eachTrack : conferenceResult.conferenceSchedulerResult()
        .result()) {
      currentCounter++;
      Assert.assertEquals(eachTrack.getTrackName(), "Track " + currentCounter);
      validateConfiguredSession(eachTrack);
      Assert.assertEquals(trackToString[currentCounter - 1], eachTrack
          .toString().trim());
    }

  }

  @Test
  public void conferenceSchedulingStrategyWithFourthInputTest() {

    ConfigurationBuilder configurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();

    Configuration configuration = configurationBuilder.buildConfiguration();
    ConferenceManager conferenceManager =
        configuration.createConferenceManager();

    List<ConferenceTalk> conferenceTalks = new LinkedList<ConferenceTalk>();
    conferenceTalks.add(new ConferenceTalk(
        "Writing Fast Tests Against Enterprise Rails", "60min"));
    conferenceTalks.add(new ConferenceTalk("Overdoing it in Python", "45min"));
    conferenceTalks.add(new ConferenceTalk("Lua for the Masses", "30min"));
    conferenceTalks.add(new ConferenceTalk(
        "Ruby Errors from Mismatched Gem Versions", "45min"));

    conferenceTalks.add(new ConferenceTalk("Common Ruby Errors", "45min"));
    conferenceTalks.add(new ConferenceTalk("Rails for Python Developers",
        "lightning"));
    conferenceTalks.add(new ConferenceTalk("Communicating Over Distance",
        "60min"));
    conferenceTalks.add(new ConferenceTalk("Accounting-Driven Development",
        "45min"));
    conferenceTalks.add(new ConferenceTalk("Woah", "30min"));
    conferenceTalks.add(new ConferenceTalk("Sit Down and Write", "30min"));
    conferenceTalks
        .add(new ConferenceTalk("Pair Programming vs Noise", "45min"));
    conferenceTalks.add(new ConferenceTalk("Rails Magic", "60min"));
    conferenceTalks.add(new ConferenceTalk(
        "Ruby on Rails: Why We Should Move On", "60min"));
    conferenceTalks.add(new ConferenceTalk("Clojure Ate Scala (on my project)",
        "45min"));
    conferenceTalks.add(new ConferenceTalk(
        "Programming in the Boondocks of Seattle", "30min"));
    conferenceTalks.add(new ConferenceTalk(
        "Ruby vs. Clojure for Back-End Development", "30min"));
    conferenceTalks.add(new ConferenceTalk(
        "Ruby on Rails Legacy App Maintenance", "60min"));
    conferenceTalks.add(new ConferenceTalk(
        "Writing Fast Tests Against Enterprise Rails", "60min"));

    ConferenceResult conferenceResult =
        conferenceManager.scheduleConference(conferenceTalks);

    Assert.assertNotNull(conferenceResult, "The result is null.");

    Assert.assertEquals(conferenceResult.validationOutCome(),
        ValidationOutCome.PASSED, "The validation failed.");

    Assert.assertEquals(conferenceResult.schedulerOutcome(),
        SchedulerOutcome.EXECUTED,
        " The strategy algorithm could not be executed successfully.");

    Assert.assertEquals(conferenceResult.conferenceSchedulerResult().result()
        .size(), 2);

    int currentCounter = 0;
    String trackToString[] =
        {
            "[Track name : Track 1, Conference Sessions : [[Session name : Morning Session, Conference Talks : [[Title : Writing Fast Tests Against Enterprise Rails, Duration : 60, Scheduled Time : 09:00 AM],[Title : Overdoing it in Python, Duration : 45, Scheduled Time : 10:00 AM],[Title : Lua for the Masses, Duration : 30, Scheduled Time : 10:45 AM],[Title : Ruby Errors from Mismatched Gem Versions, Duration : 45, Scheduled Time : 11:15 AM]], start time : 9:00 am, end time : 12:00 pm],[Session name : Lunch Session, Conference Talks : [], start time : 12:00 pm, end time : 1:00 pm],[Session name : Afternoon Session, Conference Talks : [[Title : Common Ruby Errors, Duration : 45, Scheduled Time : 01:00 PM],[Title : Rails for Python Developers, Duration : 5, Scheduled Time : 01:45 PM],[Title : Communicating Over Distance, Duration : 60, Scheduled Time : 01:50 PM],[Title : Accounting-Driven Development, Duration : 45, Scheduled Time : 02:50 PM],[Title : Woah, Duration : 30, Scheduled Time : 03:35 PM],[Title : Sit Down and Write, Duration : 30, Scheduled Time : 04:05 PM],[Title : Networking Event, Duration : 100, Scheduled Time : 5 pm]], start time : 1:00 pm, end time : 5:00 pm]]",
            "[Track name : Track 2, Conference Sessions : [[Session name : Morning Session, Conference Talks : [[Title : Pair Programming vs Noise, Duration : 45, Scheduled Time : 09:00 AM],[Title : Rails Magic, Duration : 60, Scheduled Time : 09:45 AM],[Title : Ruby on Rails: Why We Should Move On, Duration : 60, Scheduled Time : 10:45 AM]], start time : 9:00 am, end time : 12:00 pm],[Session name : Lunch Session, Conference Talks : [], start time : 12:00 pm, end time : 1:00 pm],[Session name : Afternoon Session, Conference Talks : [[Title : Clojure Ate Scala (on my project), Duration : 45, Scheduled Time : 01:00 PM],[Title : Programming in the Boondocks of Seattle, Duration : 30, Scheduled Time : 01:45 PM],[Title : Ruby vs. Clojure for Back-End Development, Duration : 30, Scheduled Time : 02:15 PM],[Title : Ruby on Rails Legacy App Maintenance, Duration : 60, Scheduled Time : 02:45 PM],[Title : Writing Fast Tests Against Enterprise Rails, Duration : 60, Scheduled Time : 03:45 PM],[Title : Networking Event, Duration : 100, Scheduled Time : 5 pm]], start time : 1:00 pm, end time : 5:00 pm]]"
        };
    for (Track eachTrack : conferenceResult.conferenceSchedulerResult()
        .result()) {
      currentCounter++;
      Assert.assertEquals(eachTrack.getTrackName(), "Track " + currentCounter);
      validateConfiguredSession(eachTrack);
      // System.out.println("track " + eachTrack);
      Assert.assertEquals(trackToString[currentCounter - 1], eachTrack
          .toString().trim());
    }

  }

  /**
   * 
   */
  private void validateConfiguredSession(
      Track track) {
    List<ConferenceSession> conferenceSessions =
        ConferenceSessionFactory.getSingletonInstance()
            .createAllConfiguredSession();
    for (ConferenceSession session : conferenceSessions) {
      Boolean validateEachSession = Boolean.FALSE;
      for (Iterator<ConferenceSession> sessionThruTrack = track
          .conferenceSessionsIterator(); sessionThruTrack.hasNext();) {
        ConferenceSession conferenceSession = sessionThruTrack.next();
        if (conferenceSession.sessionName().equals(session.sessionName())) {
          validateEachSession = Boolean.TRUE;
          // validateEachSession(conferenceSession);
          break;
        }
      }
      Assert.assertTrue(validateEachSession,
          "Session not found thru track creation.");
    }
  }

  /**
   * @param conferenceSession
   */
  /*
   * private void validateEachSession(ConferenceSession conferenceSession) { if
   * (conferenceSession instanceof LunchSession) {
   * Assert.assertEquals(conferenceSession., expected); }
   * 
   * }
   */

  @Test
  public void conferenceSchedulingStrategyWithInputStreamTest() {

    ConfigurationBuilder configurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();

    Configuration configuration = configurationBuilder.buildConfiguration();
    ConferenceManager conferenceManager =
        configuration.createConferenceManager();

    ConferenceResult conferenceResult =
        conferenceManager.scheduleConference(this.getClass().getClassLoader()
            .getResourceAsStream("conference-talk-list1.xml"));

    Assert.assertNotNull(conferenceResult, "The result is null.");

    Assert.assertEquals(conferenceResult.validationOutCome(),
        ValidationOutCome.PASSED, "The validation failed.");

    Assert.assertEquals(conferenceResult.schedulerOutcome(),
        SchedulerOutcome.EXECUTED,
        " The strategy algorithm could not be executed successfully.");

    Assert.assertEquals(conferenceResult.conferenceSchedulerResult().result()
        .size(), 1);

    int currentCounter = 0;
    String trackToString[] =
        {
            "[Track name : Track 1, Conference Sessions : [[Session name : Morning Session, Conference Talks : [[Title : Writing Fast Tests Against Enterprise Rails, Duration : 60, Scheduled Time : 09:00 AM],[Title : Overdoing it in Python, Duration : 45, Scheduled Time : 10:00 AM],[Title : Lua for the Masses, Duration : 30, Scheduled Time : 10:45 AM],[Title : Ruby Errors from Mismatched Gem Versions, Duration : 45, Scheduled Time : 11:15 AM]], start time : 9:00 am, end time : 12:00 pm],[Session name : Lunch Session, Conference Talks : [], start time : 12:00 pm, end time : 1:00 pm],[Session name : Afternoon Session, Conference Talks : [[Title : Common Ruby Errors, Duration : 45, Scheduled Time : 01:00 PM],[Title : Rails for Python Developers, Duration : 5, Scheduled Time : 01:45 PM],[Title : Communicating Over Distance, Duration : 60, Scheduled Time : 01:50 PM],[Title : Networking Event, Duration : 100, Scheduled Time : 5 pm]], start time : 1:00 pm, end time : 5:00 pm]]"
        };
    for (Track eachTrack : conferenceResult.conferenceSchedulerResult()
        .result()) {
      currentCounter++;
      Assert.assertEquals(eachTrack.getTrackName(), "Track " + currentCounter);
      validateConfiguredSession(eachTrack);
      Assert.assertEquals(trackToString[currentCounter - 1], eachTrack
          .toString().trim());
    }

  }

  @Test
  public void conferenceSchedulingStrategyWithXMLInputStreamTest() {

    ConfigurationBuilder configurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();
    configurationBuilder.setDefaultParserType(PARSERTYPE.XML);

    Configuration configuration = configurationBuilder.buildConfiguration();
    ConferenceManager conferenceManager =
        configuration.createConferenceManager();

    ConferenceResult conferenceResult =
        conferenceManager.scheduleConference(this.getClass().getClassLoader()
            .getResourceAsStream("conference-talk-list1.xml"));

    Assert.assertNotNull(conferenceResult, "The result is null.");

    Assert.assertEquals(conferenceResult.validationOutCome(),
        ValidationOutCome.PASSED, "The validation failed.");

    Assert.assertEquals(conferenceResult.schedulerOutcome(),
        SchedulerOutcome.EXECUTED,
        " The strategy algorithm could not be executed successfully.");

    Assert.assertEquals(conferenceResult.conferenceSchedulerResult().result()
        .size(), 1);

    int currentCounter = 0;
    String trackToString[] =
        {
            "[Track name : Track 1, Conference Sessions : [[Session name : Morning Session, Conference Talks : [[Title : Writing Fast Tests Against Enterprise Rails, Duration : 60, Scheduled Time : 09:00 AM],[Title : Overdoing it in Python, Duration : 45, Scheduled Time : 10:00 AM],[Title : Lua for the Masses, Duration : 30, Scheduled Time : 10:45 AM],[Title : Ruby Errors from Mismatched Gem Versions, Duration : 45, Scheduled Time : 11:15 AM]], start time : 9:00 am, end time : 12:00 pm],[Session name : Lunch Session, Conference Talks : [], start time : 12:00 pm, end time : 1:00 pm],[Session name : Afternoon Session, Conference Talks : [[Title : Common Ruby Errors, Duration : 45, Scheduled Time : 01:00 PM],[Title : Rails for Python Developers, Duration : 5, Scheduled Time : 01:45 PM],[Title : Communicating Over Distance, Duration : 60, Scheduled Time : 01:50 PM],[Title : Networking Event, Duration : 100, Scheduled Time : 5 pm]], start time : 1:00 pm, end time : 5:00 pm]]"
        };
    for (Track eachTrack : conferenceResult.conferenceSchedulerResult()
        .result()) {
      currentCounter++;
      Assert.assertEquals(eachTrack.getTrackName(), "Track " + currentCounter);
      validateConfiguredSession(eachTrack);
      Assert.assertEquals(trackToString[currentCounter - 1], eachTrack
          .toString().trim());
    }

  }

}
