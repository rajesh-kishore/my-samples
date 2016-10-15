/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.schedulingservice.spi;

import java.util.List;

import com.kishore.confmanagement.extension.schedulingstrategy.api.ConferenceSchedulerStrategy;
import com.kishore.confmanagement.extension.validators.vos.Validator;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public interface ConferenceSchedulerServiceProvider {

  /**
   * Returns the Conference scheduler algorithm.
   * 
   * @return The scheduler algorithm exposed by the provider
   *         {@link ConferenceSchedulerStrategy}.
   */
  public ConferenceSchedulerStrategy schedulerStrategy();

  /**
   * The api returns all the validators provided by provider, its unmodifiable.
   * 
   * @return The validators
   */
  public List<Validator> validators();
}
