/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.factories;

import java.util.HashMap;
import java.util.Map;

import com.kishore.confmanagement.core.resolvers.DurationResolver;
import com.kishore.confmanagement.core.resolvers.MinuteDurationResolver;

/**
 * The factory class returns the fly weight instance of {@link DurationResolver}
 * .
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class DurationResolverFlyWeightFactory {

  /**
   * The possible duration units.
   * 
   * @author Rajesh Kishore
   * @version 1.0
   * @since V1
   */
  public static enum DURATION_UNIT {
    MINUTES
  };

  /**
   * The map containing duration resolver by unit.
   */
  private final static Map<DURATION_UNIT, DurationResolver> unitByResolver =
      new HashMap<DURATION_UNIT, DurationResolver>(2);

  static {
    unitByResolver.put(DURATION_UNIT.MINUTES,
        new MinuteDurationResolver());
  }

  /**
   * Returns the duration resolver.
   * 
   * @param durationUnit {@link #getDurationResolver(DURATION_UNIT)}
   * @return The instance of {@link DurationResolver} instance
   */
  public static DurationResolver
      getDurationResolver(DURATION_UNIT durationUnit) {
    return unitByResolver.get(durationUnit);
  }
}
