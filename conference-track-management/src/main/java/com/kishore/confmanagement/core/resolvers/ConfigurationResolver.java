/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.resolvers;

import com.kishore.confmanagement.common.constants.Constants;
import com.kishore.confmanagement.common.loggers.BaseLogger;
import com.kishore.confmanagement.core.exceptions.ProviderInstantiationException;
import com.kishore.confmanagement.extension.schedulingservice.spi.ConferenceSchedulerServiceProvider;

/**
 * The utility class is responsible for resolving the
 * {@link ConferenceSchedulerServiceProvider}.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class ConfigurationResolver {

  /**
   * The logger class instance.
   */
  private static final BaseLogger LOGGER = BaseLogger.getInstance();

  /**
   * Resolves the {@link ConferenceSchedulerServiceProvider}.
   * 
   * @param providerClassName The provider class name.
   * @return The instance of {@link ConferenceSchedulerServiceProvider}.
   * @throws The exception com.kishore.confmanagement.core.exceptions.
   * ProviderInstantiationException if some error occurs.
   */
  public static ConferenceSchedulerServiceProvider
      resolveConferenceSchedulerServiceProvider(
          String providerClassName) {

    LOGGER.logFinest("provider class is " + providerClassName);
    ConferenceSchedulerServiceProvider conferenceSchedulerServiceProvider =
        null;
    try {
      conferenceSchedulerServiceProvider = (ConferenceSchedulerServiceProvider)
          Class.forName(providerClassName).newInstance();
    } catch (InstantiationException e) {
      LOGGER.logSevere(Constants.PROVIDER_CLASS_INSTANTIATION_ERROR,
          e);
      throw new ProviderInstantiationException(e);
    } catch (IllegalAccessException e) {
      LOGGER.logSevere(
          Constants.PROVIDER_CLASS_ILLEGAL_ACCESS_ERROR,
          e);
      throw new ProviderInstantiationException(e);
    } catch (ClassNotFoundException e) {
      LOGGER.logSevere(Constants.PROVIDER_CLASS_NAME_NOT_FOUND,
          e);
      throw new ProviderInstantiationException(e);
    }

    /*
     * if (conferenceSchedulerServiceProvider == null) {
     * conferenceSchedulerServiceProvider = new
     * BasicConferenceSchedulerProvider(); }
     */
    LOGGER.logFinest("Returning succesfully");
    return conferenceSchedulerServiceProvider;
  }
}
