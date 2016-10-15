/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.tests.vos.talks;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.kishore.confmanagement.common.constants.Constants;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;
import com.kishore.confmanagement.core.vos.talks.SpecialConferenceTalk;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class ConferenceTalkTest {

  @Test
  public void conferenceTalkCreationTest() {
    ConferenceTalk talk = new ConferenceTalk("My way of coding","15min");
    Assert.assertNotNull(talk, "Error in object creation");
    Assert.assertEquals(talk.conferenceTalkTitle(), "My way of coding");
    Assert.assertEquals(talk.talkDuration(), 15);
  }

  @Test
  public void resolvedConferenceTalkDurationUnitTest() {
    ConferenceTalk talk = new ConferenceTalk("My way of coding","15min");
    Assert.assertEquals(talk.talkDurationUnit(), Constants.MINUTE);
  }

  @Test
  public void conferenceTalkEqualsTest() {
    ConferenceTalk talk = new ConferenceTalk("My way of coding","15min");

    ConferenceTalk secondTalk = new ConferenceTalk("My way of coding","20min");
    Assert.assertEquals(secondTalk, talk, "Conference talks are different");

  }

  @Test
  public void conferenceTalkNotEqualsTest() {
    ConferenceTalk talk = new ConferenceTalk("My way of coding","15min");

    ConferenceTalk secondTalk = new ConferenceTalk("My way of coding1","15min");
    Assert.assertNotEquals(secondTalk, talk, "Conference talks are different");

  }

  @Test
  public void conferenceTalkScheduledStartTimeTest() {
    ConferenceTalk talk = new ConferenceTalk("My way of coding","15min");
    Assert.assertNull(talk.scheduledStartTime(),
        "Initially shcedule time should be null");
    talk.setScheduledTime("11 am");
    Assert.assertEquals(talk.scheduledStartTime(),
        "11 am");

  }

  @Test
  public void defaultNoConferenceTalkTest() {
    ConferenceTalk talk = ConferenceTalk.NO_DEFAULT_CONFERENCE_TALK;
    ConferenceTalk anotherTalk = ConferenceTalk.NO_DEFAULT_CONFERENCE_TALK;
    Assert.assertEquals(talk,
        anotherTalk);
  }

  @Test
  public void specialNetworkingTalkTest() {
    ConferenceTalk talk =
        new SpecialConferenceTalk(SpecialConferenceTalk.NETWORKING_EVENT,
            "15min");
    Assert.assertFalse(talk.isTalkScheduledTimeFlexible(),
        "Special talks are not flexible");
  }

  @Test
  public void normalTalkScheduledTimeFlexibilityTest() {
    ConferenceTalk talk =
        new ConferenceTalk("My way of teaching",
            "15min");
    Assert.assertTrue(talk.isTalkScheduledTimeFlexible(),
        "Normal talks are flexible");
  }
}
