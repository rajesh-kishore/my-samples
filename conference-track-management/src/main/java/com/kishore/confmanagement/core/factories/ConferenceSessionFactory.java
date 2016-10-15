/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.factories;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.kishore.confmanagement.common.loggers.BaseLogger;
import com.kishore.confmanagement.core.vos.sessions.AfterNoonSession;
import com.kishore.confmanagement.core.vos.sessions.ConferenceSession;
import com.kishore.confmanagement.core.vos.sessions.LunchSession;
import com.kishore.confmanagement.core.vos.sessions.MorningSession;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class ConferenceSessionFactory {

  /**
   * The possible session types.
   * 
   * @author Rajesh Kishore
   * @version 1.0
   * @since V1
   */
  public static enum ConferenceSessionTypes {
    MORNING_SESSION, LUNCH_SESSION, AFTERNOON_SESSION
  };

  /**
   * The logger instance.
   */
  private static final BaseLogger LOGGER = BaseLogger.getInstance();

  /**
   * The private constructor.
   */
  private ConferenceSessionFactory() {

  }

  /**
   * Returns all configured sessions.
   * 
   * @return The unmodifiable list of configured session.
   */
  public List<ConferenceSession> createAllConfiguredSession() {

    final List<ConferenceSession> conferenceSessions =
        new LinkedList<ConferenceSession>();

    for (ConferenceSessionTypes type : ConferenceSessionTypes
        .values()) {
      conferenceSessions.add(createConferenceSessionByType(type));
    }

    return Collections.unmodifiableList(conferenceSessions);
  }

  /**
   * The singlegton instance creator to parent class , acting as a helper to
   * create instance of parent class.
   * 
   * @author Rajesh Kishore
   * @version 1.0
   * @since V1
   */
  private static class ConferenceSessionFactorySingletonCreator {
    private static ConferenceSessionFactory conferenceSessionFactory =
        new ConferenceSessionFactory();
  }

  /**
   * The api returns the singleton instance of this class.
   * 
   * @return The singleton instance of this class.
   */
  public static ConferenceSessionFactory getSingletonInstance() {
    return ConferenceSessionFactorySingletonCreator.conferenceSessionFactory;
  }

  /**
   * @param sessionType one of the session types defined in
   *          {@link ConferenceSessionFactory}. Default is {@link LunchSession}
   * @return The instance of {@link ConferenceSession}
   */
  ConferenceSession createConferenceSessionByType(
      ConferenceSessionTypes sessionType) {

    LOGGER.logFinest("The sessionType is " + sessionType);

    switch (sessionType) {
    case MORNING_SESSION:
      return new MorningSession();
    case AFTERNOON_SESSION:
      return new AfterNoonSession();
    case LUNCH_SESSION:
      return new LunchSession();
    }

    LOGGER.logFinest("Successfully created session with sessionType "
        + sessionType);
    return new LunchSession();

  }
}
