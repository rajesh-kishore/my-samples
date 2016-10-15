/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.defaultextensionimpl.validator;

import java.util.Iterator;

import com.kishore.confmanagement.common.constants.Constants;
import com.kishore.confmanagement.core.vos.sessions.ConferenceSession;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;
import com.kishore.confmanagement.extension.validators.api.Validatable;
import com.kishore.confmanagement.extension.validators.vos.Validator;

/**
 * The validator's class does the validation of talks which has more duration
 * than the session alloted one.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public class MaxConferenceTalkDurationValidator implements Validator {

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.kishore.confmanagement.core.validators.Validator#validate(com.
   * thoughtworks.confmanagement.core.validators.Validatable)
   */
  @Override
  public Boolean validate(Validatable validatable) {
    Iterator<ConferenceTalk> conferenceTalkIterator =
        validatable.conferenceTalksIterator();

    while (conferenceTalkIterator.hasNext()) {
      ConferenceTalk conferenceTalk = conferenceTalkIterator.next();
      Iterator<ConferenceSession> conferenceSessionIterator =
          validatable.conferenceSessionIterator();

      Boolean validDuration = Boolean.FALSE;
      LOGGER.logFinest(
          "inside validation iterator has "
              + conferenceSessionIterator.hasNext());

      while (conferenceSessionIterator.hasNext()) {
        ConferenceSession conferenceSession = conferenceSessionIterator.next();
        LOGGER.logInfo(
            "inside validation " + conferenceSession.sessionName(), null);

        if (conferenceSession.isTalkApplicable(conferenceTalk)) {
          LOGGER.logInfo(
              conferenceTalk.conferenceTalkTitle()
                  + " has talk duration within the limit , its "
                  + conferenceTalk.talkDuration()
                  + " , the validator passed is " + this, null);
          validDuration = Boolean.TRUE;
          break;
        }
      }
      if (validDuration == Boolean.FALSE) {
        LOGGER.logInfo(
            Constants.CONFERENCE_TALK_TOO_LARGE_DURATION,
            new Object[] { conferenceTalk.conferenceTalkTitle() });
        return validDuration;
      }
    }
    return Boolean.TRUE;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return this.getClass().getName();
  }
}
