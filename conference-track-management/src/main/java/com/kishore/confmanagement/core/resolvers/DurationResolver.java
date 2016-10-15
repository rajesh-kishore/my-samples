/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.resolvers;

import java.util.Map;

import com.kishore.confmanagement.common.constants.Constants;
import com.kishore.confmanagement.common.loggers.BaseLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public interface DurationResolver {

  public final BaseLogger LOGGER = BaseLogger.getInstance();

  /**
   * The utility api to return the time duration in respective unit.
   * 
   * @param startTime The start time in format 10:00 am or 10:00 am. or 10:00 AM
   *          or 10:00 AM.
   * @param endTime The end time in format 10:00 am or 10:00 am. or 10:00 AM or
   *          10:00 AM.
   * @return The duration in respective unit such as hour or min implemented by
   *         subclass.
   */
  public int resolveTimeDurationInUnit(String startTime,
      String endTime);

  /**
   * The utility api to return the actual start time in time format such as
   * 10:00 am.
   * 
   * @param endTime The start time in format 10:00 am or 10:00 am. or 10:00 AM
   *          or 10:00 AM.
   * @param leftOverTimeInUnit The left over time in respective unit.
   * @return The start time in time format.
   */
  public String resolveTimeinHourFormat(String endTime,
      int leftOverTimeInUnit);

  /**
   * The utility api returns the resolved talk duration in respective unit.In
   * case of error it returns 5;
   * 
   * @param talkDurationWithUnit The talk duration in respective unit , correct
   *          api expectation format - 20unit or lightening
   * @return The map containing talk duration info. One key contains duration
   *         info in Integer and other contains unit info.The keys are
   *         {@link Constants#RESOLVEDDURATION} and
   *         {@link Constants#RESOLVEDUNIT}
   */
  public Map<String, Object> talkDurationResolver(String talkDurationWithUnit);
}
