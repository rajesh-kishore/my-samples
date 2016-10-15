/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core;

import java.io.InputStream;
import java.util.List;

import com.kishore.confmanagement.common.constants.Constants;
import com.kishore.confmanagement.common.loggers.BaseLogger;
import com.kishore.confmanagement.core.vos.ConferenceResult;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;

/**
 * The class is customer facing class to perform different operations. 
 <code>
  Configuration configuration =
  ConfigurationBuilder.defaultConfigurationBuilder().buildConfiguration();
  ConferenceManager manager = configuration.conferenceManagerEngine();
  manager.scheduleConference(conferenceTalks);
 </code>
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class ConferenceManager {

  /**
   * The logger instance.
   */
  final private static BaseLogger LOGGER = BaseLogger.getInstance();

  /**
   * The immutable {@link ConferenceManagerEngine} instance,responsible for
   * inner working of {@link ConferenceManager}.
   */
  private final ConferenceManagerEngine conferenceManagerEngine;

  /**
   * The only package level constructor for internal use by
   * {@link Configuration}. The instance of this class cannot be created
   * directly from client.
   * 
   * @param configuration The instance of {@link Configuration}.
   */
  ConferenceManager(Configuration configuration) {
    LOGGER
        .logFinest("The instance of ConferenceManager being"
            + "created with configuration "
            + configuration);
    conferenceManagerEngine =
        ConferenceManagerEngine
            .createConferenceManagerEngineByConfiguration(configuration);
  }

  /*
   * /** The singleton creator class which is used to create the singleton
   * instance of ConferenceManager.
   * 
   * @version 1.0
   * 
   * @since V1
   */
  /*
   * private static class ConferenceManagerSingletonCreator {
   * 
   * private static final ConferenceManager conferenceManager = new
   * ConferenceManager();
   * 
   * private static ConferenceManager getConferenceManager() { return
   * conferenceManager; } }
   */

  /**
   * Creates or retrieves the singleton instance of ConferenceManager.
   * 
   * @param configurator The configuration
   * @return {@link ConferenceManager} , the Conference Manager.
   */
  /*
   * public static ConferenceManager createSingletonInstance( Configuration
   * configurator) { return
   * ConferenceManagerSingletonCreator.getConferenceManager(); }
   */

  /**
   * The api to schedule the list of {@link ConferenceTalk}.
   * 
   * @param conferenceTalks the conference talk list to be scheduled.
   * @return The {@link ConferenceResult} instance
   */
  public ConferenceResult scheduleConference(
      List<ConferenceTalk> conferenceTalks) {

    LOGGER.logInfo(Constants.CONFERENCE_TALKS_FOR_SCHEDULING_SUBMITTED,
        new Object[] { conferenceTalks });
    final ConferenceResult conferenceResult = conferenceManagerEngine().submit(
        conferenceTalks);
    LOGGER.logInfo(Constants.CONFERENCE_SCHEDULING_RESULT,
        new Object[] { conferenceResult });
    return conferenceResult;
  }

  /**
   * Returns The instance to {@link ConferenceManagerEngine}.
   * 
   * @return the conferenceManagerEngine
   */
  private final ConferenceManagerEngine conferenceManagerEngine() {
    return conferenceManagerEngine;
  }

  /**
   * @param fileStream The file stream representing talks list, could be either
   *          in xml or json format.
   * @return The {@link ConferenceResult} instance
   * @throws The exception com.kishore.confmanagement.core.
   * exceptions.ParsingException when data is not in one of
   *         the format.
   */
  public ConferenceResult scheduleConference(InputStream fileStream) {
    LOGGER.logFinest("The file input is there for scheduling");
    ConferenceResult result = conferenceManagerEngine().submit(fileStream);
    LOGGER.logFinest("The file input scheduleConference completed");
    return result;
  }
}
