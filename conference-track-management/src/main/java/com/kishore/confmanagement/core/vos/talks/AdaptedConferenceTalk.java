/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.vos.talks;

import javax.xml.bind.annotation.XmlElement;

/**
 * The adapted class for {@link ConferenceTalk} as it does not have default no
 * arg constructor which is required for JAXB.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public class AdaptedConferenceTalk {

  /**
   * Holds the title
   */
  private String conferenceTalkTitle;

  /**
   * Holds time and unit information.
   */
  private String talkDurationWithUnit;

  /**
   * @return the conferenceTalkTitle
   */
  @XmlElement
  public final String getConferenceTalkTitle() {
    return conferenceTalkTitle;
  }

  /**
   * @return the talkDurationWithUnit
   */
  @XmlElement
  public final String getTalkDurationWithUnit() {
    return talkDurationWithUnit;
  }

  /**
   * @param conferenceTalkTitle the conferenceTalkTitle to set
   */
  public final void setConferenceTalkTitle(String conferenceTalkTitle) {
    this.conferenceTalkTitle = conferenceTalkTitle;
  }

  /**
   * @param talkDurationWithUnit the talkDurationWithUnit to set
   */
  public final void setTalkDurationWithUnit(String talkDurationWithUnit) {
    this.talkDurationWithUnit = talkDurationWithUnit;
  }

}
