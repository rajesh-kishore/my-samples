/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.vos.tracks;

import com.kishore.confmanagement.core.generators.DefaultTrackIdNameGenerator;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public class StandardTrack extends Track {

  /**
   * The default constructor.
   * 
   * @param sequence the sequence.
   */
  public StandardTrack(Integer sequence) {
    super(new DefaultTrackIdNameGenerator(sequence));
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.core.vos.tracks.Track#toString()
   */
  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return super.toString();
  }
}
