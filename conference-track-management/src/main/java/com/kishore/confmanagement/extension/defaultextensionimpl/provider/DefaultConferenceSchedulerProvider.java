/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.extension.defaultextensionimpl.provider;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.kishore.confmanagement.extension.defaultextensionimpl.schedulingstrategy.FirstFitStrategy;
import com.kishore.confmanagement.extension.defaultextensionimpl.validator.MaxConferenceTalkDurationValidator;
import com.kishore.confmanagement.extension.schedulingservice.spi.ConferenceSchedulerServiceProvider;
import com.kishore.confmanagement.extension.schedulingstrategy.api.ConferenceSchedulerStrategy;
import com.kishore.confmanagement.extension.validators.vos.Validator;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class DefaultConferenceSchedulerProvider implements
    ConferenceSchedulerServiceProvider {

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.schedulingservice.spi.
   * ConferenceSchedulerServiceProvider#schedulerStrategy()
   */
  @Override
  public ConferenceSchedulerStrategy schedulerStrategy() {
    return new FirstFitStrategy();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.schedulingservice.spi.
   * ConferenceSchedulerServiceProvider#validators()
   */
  @Override
  public List<Validator> validators() {
    List<Validator> validators = new LinkedList<Validator>();
    validators.add(new MaxConferenceTalkDurationValidator());
    return Collections.unmodifiableList(validators);
  }

}
