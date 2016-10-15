/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.factories;

import com.kishore.confmanagement.core.vos.tracks.StandardTrack;
import com.kishore.confmanagement.core.vos.tracks.Track;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class TrackFactory {

  /**
   * The track, types.
   * 
   * @author Rajesh Kishore
   * @version 1.0
   * @since V1
   */
  public static enum TrackTypes {
    STANDARD_TRACK
  };

  /**
   * The factory method to create the track.
   * 
   * @param trackType one of the types mentioned in {@link TrackTypes}
   * @param sequence to be passed by the consumer.
   * @return instance of one of the sub types of {@link Track}
   */
  public static Track createTrack(TrackTypes trackType, Integer sequence) {
    if (trackType == TrackTypes.STANDARD_TRACK) {
      return new StandardTrack(sequence);
    }
    return new StandardTrack(sequence);
  }
}
