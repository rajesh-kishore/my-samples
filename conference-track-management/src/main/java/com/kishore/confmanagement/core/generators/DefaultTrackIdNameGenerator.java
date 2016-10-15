/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.generators;

import com.kishore.confmanagement.core.vos.tracks.Track;

/**
 * The class generates id and name for {@link Track}
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public class DefaultTrackIdNameGenerator implements TrackIdNameGenerator {

  /**
   * The sequence passed by consumer.
   */
  private Integer sequence = null;

  /**
   * The default prefix name for Track.
   */
  private final static String DEFAULT_PREFIX = "Track ";

  /**
   * The default constructor based on sequence.
   * 
   * @param sequence The generated sequence passed by client.
   */
  public DefaultTrackIdNameGenerator(Integer sequence) {
    setSequence(sequence == null ? 1 : sequence);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.core.generators.TrackIdNameGenerator#
   * generateTrackName()
   */
  @Override
  public String generateTrackName() {
    return DEFAULT_PREFIX + sequence.toString();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.core.generators.
   * TrackIdNameGenerator#generateTrackId()
   */
  @Override
  public String generateTrackId() {
    return getSequence().toString();
  }

  /**
   * @return the sequence
   */
  private final Integer getSequence() {
    return sequence;
  }

  /**
   * @param sequence the sequence to set
   */
  private final void setSequence(Integer sequence) {
    this.sequence = sequence;
  }

}
