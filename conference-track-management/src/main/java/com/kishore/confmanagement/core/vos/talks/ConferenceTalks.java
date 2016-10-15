/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.vos.talks;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The class is designed solely for the JAXB purpose.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
@XmlRootElement( name = "conferencetalks" )
@XmlAccessorType( XmlAccessType.FIELD )
public final class ConferenceTalks {

  @XmlElement( name = "conferencetype" )
  List<ConferenceTalk> conferenctalkLst = new LinkedList<ConferenceTalk>();

  /**
   * the conference talks list.
   * 
   * @return the conferenctalks
   */
  public final List<ConferenceTalk> getConferenctalkLst() {
    return conferenctalkLst;
  }

  /**
   * Sets the conference talks.
   * 
   * @param conferenctalks the conferenctalks to set
   */
  public final void setConferenctalkLst(List<ConferenceTalk> conferenctalks) {
    this.conferenctalkLst = conferenctalks;
  }
}
