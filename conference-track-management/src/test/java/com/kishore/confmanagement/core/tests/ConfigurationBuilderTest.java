/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.kishore.confmanagement.core.Configuration;
import com.kishore.confmanagement.core.ConfigurationBuilder;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class ConfigurationBuilderTest {

  /*
   * @Test public void singletonConferenceManagerTest() { ConferenceManager
   * conferenceManager = ConferenceManager.createSingletonInstance(null);
   * ConferenceManager conferenceManagerSecondInstance =
   * ConferenceManager.createSingletonInstance(null);
   * 
   * Assert.assertEquals(conferenceManager, conferenceManagerSecondInstance,
   * "ConferenceManager is not singleton");
   * 
   * }
   * 
   * public void singlet
   */

  @Test
  public void defaultConfigurationBuilderInstanceTest() {

    ConfigurationBuilder configurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();

    ConfigurationBuilder anotherConfigurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();

    Assert.assertEquals(configurationBuilder,
        anotherConfigurationBuilder,
        "Configuration Builder instance is not same.");
  }

  @Test
  public void defaultConfigurationByDefaultConfigurationBuilderAPITest() {

    ConfigurationBuilder configurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();

    ConfigurationBuilder anotherConfigurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();

    Assert.assertEquals(configurationBuilder,
        anotherConfigurationBuilder,
        "Configuration Builder instance is not same.");

    Configuration configuration =
        configurationBuilder.buildConfiguration();
    Configuration configurationAnotherInstance =
        configurationBuilder.buildConfiguration();

    Assert.assertEquals(configuration.talkLengthUnit(),
        configurationAnotherInstance.talkLengthUnit(),
        "Configuration instance is not same.");

    Assert.assertEquals(configuration.conferenceSchedulerServiceProvider(),
        configurationAnotherInstance.conferenceSchedulerServiceProvider(),
        "Configuration instance is not same.");

    /*
     * Assert.assertEquals(configuration.conferenceSessions().size(),
     * configurationAnotherInstance.conferenceSessions().size(),
     * "Configuration instance is not same.");
     */
    /*
     * Assert.assertEquals(configuration.defaultMandatoryTalk(),
     * configurationAnotherInstance.defaultMandatoryTalk(),
     * "Configuration instance is not same.");
     */

    Assert.assertEquals(configuration.totalPossibleDurationForTheDay(),
        configurationAnotherInstance.totalPossibleDurationForTheDay(),
        "Configuration instance is not same.");

  }

  @Test
  public void explicitConfigurationToConfigurationBuilderTest() {
    Configuration configuration =
        new ConfigurationBuilder(
            ConfigurationBuilder.DEFAULT_CONFERENCE_SCHEDULER_SERVICE_PROVIDER)
            .buildConfiguration();
    Configuration configurationAnotherInstance =
        new ConfigurationBuilder(
            ConfigurationBuilder.DEFAULT_CONFERENCE_SCHEDULER_SERVICE_PROVIDER)
            .buildConfiguration();

    Assert.assertEquals(configuration.talkLengthUnit(),
        configurationAnotherInstance.talkLengthUnit(),
        "Configuration instance is not same.");

    Assert.assertEquals(configuration.conferenceSchedulerServiceProvider(),
        configurationAnotherInstance.conferenceSchedulerServiceProvider(),
        "Configuration instance is not same.");

    /*
     * Assert.assertEquals(configuration.conferenceSessions().size(),
     * configurationAnotherInstance.conferenceSessions().size(),
     * "Configuration instance is not same.");
     */
    /*
     * Assert.assertEquals(configuration.defaultMandatoryTalk(),
     * configurationAnotherInstance.defaultMandatoryTalk(),
     * "Configuration instance is not same.");
     */

    Assert.assertEquals(configuration.totalPossibleDurationForTheDay(),
        configurationAnotherInstance.totalPossibleDurationForTheDay(),
        "Configuration instance is not same.");

  }

  @Test
  public void proveNonSingletonConfigurationInstanceByDefaultConfigurationBuilderTest() {

    ConfigurationBuilder configurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();

    Configuration configuration = configurationBuilder.buildConfiguration();
    Assert.assertNotEquals(configuration,
        configurationBuilder.buildConfiguration(),
        "Configuration Builder instance is same.");

  }

}
