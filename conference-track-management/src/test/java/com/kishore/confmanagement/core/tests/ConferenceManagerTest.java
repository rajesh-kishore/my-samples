/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.tests;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.kishore.confmanagement.core.ConferenceManager;
import com.kishore.confmanagement.core.Configuration;
import com.kishore.confmanagement.core.ConfigurationBuilder;
import com.kishore.confmanagement.extension.defaultextensionimpl.schedulingstrategy.FirstFitStrategy;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public class ConferenceManagerTest {

  @Test
  public void configurationManagerTest() {

    ConfigurationBuilder configurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();

    Configuration configuration = configurationBuilder.buildConfiguration();
    ConferenceManager conferenceManager =
        configuration.createConferenceManager();
    Assert.assertNotEquals(conferenceManager,
        null,
        "ConfigurationManager is null.");

  }

  @Test
  public void proveNonSingletonConfigurationManagerTest() {

    ConfigurationBuilder configurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();

    Configuration configuration = configurationBuilder.buildConfiguration();
    ConferenceManager conferenceManager =
        configuration.createConferenceManager();
    Assert.assertNotEquals(conferenceManager,
        configuration.createConferenceManager(),
        "ConfigurationManager is singleton.");

  }

  @Test
  public void resolvedConferenceScheduleStratgeyTest() {

    ConfigurationBuilder configurationBuilder =
        ConfigurationBuilder.defaultConfigurationBuilder();

    Configuration configuration = configurationBuilder.buildConfiguration();
    ConferenceManager conferenceManager =
        configuration.createConferenceManager();

    // following requires reflection as classes are restricted to package level
    Class noParams[] = {};
    Method method = null;
    try {

      method =
          ConferenceManager.class.getDeclaredMethod("conferenceManagerEngine",
              noParams);
    } catch (NoSuchMethodException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
      Assert.assertFalse(true);
    } catch (SecurityException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
      Assert.assertFalse(true);
    }
    method.setAccessible(true);
    Object engine = null;
    try {
      engine =
          method.invoke(conferenceManager, noParams);
    } catch (IllegalAccessException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
      Assert.assertFalse(true);
    } catch (IllegalArgumentException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
      Assert.assertFalse(true);
    } catch (InvocationTargetException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
      Assert.assertFalse(true);
    }
    Field field = null;
    try {
      field = engine.getClass()
          .getDeclaredField("resolvedConfiguration");
    } catch (NoSuchFieldException | SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      Assert.assertFalse(true);
    }
    field.setAccessible(true);

    Object resolvedConfiguration = null;
    try {
      resolvedConfiguration = field.get(engine);
    } catch (IllegalArgumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      Assert.assertFalse(true);
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      Assert.assertFalse(true);
    }

    Field stratgeyField = null;
    try {
      stratgeyField = resolvedConfiguration.getClass().getDeclaredField(
          "conferenceSchedulerStrategy");
    } catch (NoSuchFieldException | SecurityException e) {
      e.printStackTrace();
      Assert.assertFalse(true);
    }
    stratgeyField.setAccessible(true);

    Object resolvedStrategy = null;

    try {
      resolvedStrategy = stratgeyField.get(resolvedConfiguration);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      e.printStackTrace();
      Assert.assertFalse(true);
    }

    Assert
        .assertTrue(
            resolvedStrategy
            instanceof FirstFitStrategy,
            "The default provider stratgey is not first fit.");

  }
  /*
   * @Test public void
   * proveNonSingletonConfigurationInstanceByDefaultConfigurationBuilderTest() {
   * 
   * ConfigurationBuilder configurationBuilder =
   * ConfigurationBuilder.defaultConfigurationBuilder();
   * 
   * Configuration configuration = configurationBuilder.buildConfiguration();
   * Assert.assertNotEquals(configuration,
   * configurationBuilder.buildConfiguration(),
   * "Configuration Builder instance is same.");
   * 
   * }
   */

}
