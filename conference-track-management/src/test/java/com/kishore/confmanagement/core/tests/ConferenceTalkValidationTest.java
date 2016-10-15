/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.tests;

import java.util.LinkedList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.kishore.confmanagement.core.ConferenceManager;
import com.kishore.confmanagement.core.Configuration;
import com.kishore.confmanagement.core.ConfigurationBuilder;
import com.kishore.confmanagement.core.vos.ConferenceResult;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;
import com.kishore.confmanagement.extension.validators.vos.ValidationResult.ValidationOutCome;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public class ConferenceTalkValidationTest {

  @Test
  public void conferenceTalkValidationExceedingMaxDurationTest() {

    ConfigurationBuilder configurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();

    Configuration configuration = configurationBuilder.buildConfiguration();
    ConferenceManager conferenceManager =
        configuration.createConferenceManager();

    List<ConferenceTalk> conferenceTalks = new LinkedList<ConferenceTalk>();
    conferenceTalks.add(new ConferenceTalk(
        "Writing Fast Tests Against Enterprise Rails","260min"));
    conferenceTalks.add(new ConferenceTalk("Overdoing it in Python","45min"));
    conferenceTalks.add(new ConferenceTalk("Lua for the Masses","30min"));
    conferenceTalks.add(new ConferenceTalk(
        "Ruby Errors from Mismatched Gem Versions","45min"));
    conferenceTalks.add(new ConferenceTalk("Common Ruby Errors","45min"));
    conferenceTalks.add(new ConferenceTalk("Rails for Python Developers",
        "lightning"));
    conferenceTalks.add(new ConferenceTalk("Communicating Over Distance",
        "60min"));
    conferenceTalks.add(new ConferenceTalk("Accounting-Driven Development",
        "45min"));
    conferenceTalks.add(new ConferenceTalk("Woah","30min"));
    conferenceTalks.add(new ConferenceTalk("Sit Down and Write","30min"));
    conferenceTalks
        .add(new ConferenceTalk("Pair Programming vs Noise","45min"));
    conferenceTalks.add(new ConferenceTalk("Rails Magic","60min"));
    conferenceTalks.add(new ConferenceTalk(
        "Ruby on Rails: Why We Should Move On","60min"));
    conferenceTalks.add(new ConferenceTalk("Clojure Ate Scala (on my project)",
        "45min"));
    conferenceTalks.add(new ConferenceTalk(
        "Programming in the Boondocks of Seattle","30min"));
    conferenceTalks.add(new ConferenceTalk(
        "Ruby vs. Clojure for Back-End Development","30min"));
    conferenceTalks.add(new ConferenceTalk(
        "Ruby on Rails Legacy App Maintenance","60min"));
    conferenceTalks.add(new ConferenceTalk(
        "Writing Fast Tests Against Enterprise Rails","60min"));

    conferenceTalks
        .add(new ConferenceTalk("A World Without HackerNews","30min"));
    conferenceTalks.add(new ConferenceTalk("User Interface CSS in Rails Apps",
        "30min"));

    ConferenceResult conferenceResult =
        conferenceManager.scheduleConference(conferenceTalks);

    Assert.assertNotNull(conferenceResult, "The result is null.");

    Assert.assertEquals(conferenceResult.validationOutCome(),
        ValidationOutCome.FAILED, "The validation failed.");

  }
}
