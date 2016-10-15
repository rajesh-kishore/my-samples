/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.resolvers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.kishore.confmanagement.common.constants.Constants;

/**
 * The utility class provides the api to resolve the time duration.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class MinuteDurationResolver implements DurationResolver {

  private static final SimpleDateFormat TIME_DATE_FORMAT =
      new SimpleDateFormat("hh:mm a");

  /**
   * The utility api to return the time duration in minutes.
   * 
   * @param startTime The start time in format 10:00 am or 10:00 am. or 10:00 AM
   *          or 10:00 AM.
   * @param endTime The end time in format 10:00 am or 10:00 am. or 10:00 AM or
   *          10:00 AM.
   * @return The duration in minute.
   */
  @Override
  public int resolveTimeDurationInUnit(String startTime,
      String endTime) {

    int totalDurationInMinutes = 0;

    try {

      final long parsedStartTime =
          TIME_DATE_FORMAT.parse(startTime).getTime();
      final long parsedEndTime = TIME_DATE_FORMAT.parse(endTime).getTime();
      int difference = 0;
      difference = (int) (parsedEndTime - parsedStartTime);
      totalDurationInMinutes =
          (difference < 0 ? -difference : difference) / 60000;
      return totalDurationInMinutes;
    } catch (ParseException e) {
      LOGGER.logSevere(Constants.EXCEPTION_IN_PARSING,
          e);
    }
    return totalDurationInMinutes;
  }

  /**
   * The utility api to return the actual start time in time format such as
   * 10:00 am.
   * 
   * @param endTime The start time in format 10:00 am or 10:00 am. or 10:00 AM
   *          or 10:00 AM.
   * @param leftOverTimeInMinute The left over time in minute.
   * @return The start time in time format.
   */
  @Override
  public String resolveTimeinHourFormat(String endTime,
      int leftOverTimeInMinute) {

    Date date = null;
    try {
      date = TIME_DATE_FORMAT.parse(endTime);
    } catch (ParseException e) {
      LOGGER.logSevere(Constants.EXCEPTION_IN_PARSING,
          e);
      return endTime;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.MINUTE, -leftOverTimeInMinute);
    String actualStarTime = TIME_DATE_FORMAT.format(cal.getTime());

    return actualStarTime;
  }

  /**
   * The utility api returns the resolved talk duration in minute.In case of
   * error it returns 5;
   * 
   * @param talkDurationWithUnit The talk duration in minute , correct api
   *          expectation format - 20min or lightening
   * @return The resolved duration and unit as map.
   */
  @Override
  public Map<String, Object> talkDurationResolver(String talkDurationWithUnit) {

    Map<String, Object> resolvedTalkDuration = new HashMap<String, Object>();
    resolvedTalkDuration.put("RESOLVEDUNIT", "min");

    Integer resolvedDuration = 5;
    resolvedTalkDuration.put("RESOLVEDDURATION", resolvedDuration);
    if (talkDurationWithUnit == null) {
      LOGGER.logFinest(resolvedDuration.toString());
      return resolvedTalkDuration;
    }

    if ("lightning".equalsIgnoreCase(talkDurationWithUnit)) {
      LOGGER.logFinest("lightning duration " + talkDurationWithUnit);
      return resolvedTalkDuration;
    }

    if (!talkDurationWithUnit.contains("min")) {
      return resolvedTalkDuration;
    }

    LOGGER.logFinest(talkDurationWithUnit.substring(0,
        talkDurationWithUnit.indexOf("min")));

    String extractedTalkDuration = talkDurationWithUnit.substring(0,
        talkDurationWithUnit.indexOf("min")).trim();

    try {
      resolvedDuration = Integer.valueOf(extractedTalkDuration);
      resolvedTalkDuration.put("RESOLVEDDURATION", resolvedDuration);
    } catch (Exception e) {
      LOGGER.logSevere(Constants.EXCEPTION_IN_PARSING,
          e);
      return resolvedTalkDuration;
    }

    return resolvedTalkDuration;
  }
}
