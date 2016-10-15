/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.generators;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public interface TrackIdNameGenerator {

  /**
   * Returns the generated track name. May be overridden by subclass.
   * 
   * @return The generated track name.
   */
  public abstract String generateTrackName();

  /**
   * Returns the generated track id. May be overridden by subclass.
   * 
   * @return The generated track id.
   */
  public abstract String generateTrackId();

}