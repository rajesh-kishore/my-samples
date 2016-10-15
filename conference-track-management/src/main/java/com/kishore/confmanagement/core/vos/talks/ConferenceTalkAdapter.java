/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.vos.talks;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class ConferenceTalkAdapter extends
    XmlAdapter<AdaptedConferenceTalk, ConferenceTalk> {

  /*
   * (non-Javadoc)
   * 
   * @see
   * javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
   */
  @Override
  public ConferenceTalk unmarshal(AdaptedConferenceTalk v) throws Exception {
    return new ConferenceTalk(v.getConferenceTalkTitle(),
        v.getTalkDurationWithUnit());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
   */
  @Override
  public AdaptedConferenceTalk marshal(ConferenceTalk v) throws Exception {
    AdaptedConferenceTalk conferenceTalk = new AdaptedConferenceTalk();
    conferenceTalk.setConferenceTalkTitle(v.conferenceTalkTitle());
    conferenceTalk.setTalkDurationWithUnit(v.talkDuration()
        + v.talkDurationUnit());
    return null;
  }

}
