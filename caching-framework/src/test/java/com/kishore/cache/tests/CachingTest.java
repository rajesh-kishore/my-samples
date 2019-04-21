/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.cache.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kishore.caching.api.Cache;
import com.kishore.caching.api.Configuration;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public class CachingTest {

  /**
   * @throws java.lang.Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testLRUDefaultConfigCache() {

    Configuration<String, Integer> defaultConfiguration = new Configuration<>();
    Cache<String, Integer> cache = defaultConfiguration.build();
    cache.put("Rajesh", 10);
    Assert.assertTrue(cache.get("Rajesh").equals(Integer.valueOf(10)));
  }

  @Test
  public void testLRUMaxSizeConfigCache() {

    Configuration<String, Integer> defaultConfiguration = new Configuration<>();
    Cache<String, Integer> cache = defaultConfiguration.setMaxSize(2).build();
    cache.put("Rajesh", 10);
    cache.put("Rahul", 11);
    cache.put("Vivek", 12);
    Assert.assertTrue(cache.get("Rajesh") == null);
    Assert.assertTrue(cache.get("Rahul").equals(Integer.valueOf(11)));
  }

  @Test
  public void testCustomLRUCacheStrategyConfigCache() {

    Configuration<String, Integer> defaultConfiguration = new Configuration<>();
    Cache<String, Integer> cache =
        defaultConfiguration.setStrategyProvider(
            "com.supplier.impl.CustomStrategyProvider").build();
    cache.put("Rajesh", 10);
    Assert.assertTrue(cache.get("Rajesh").equals(Integer.valueOf(10)));
  }

  @Test
  public void testCustomLRUCacheStrategyWithMaxSize() {

    Configuration<String, Integer> defaultConfiguration = new Configuration<>();
    Cache<String, Integer> cache =
        defaultConfiguration.setStrategyProvider(
            "com.supplier.impl.CustomStrategyProvider").setMaxSize(2)
            .build();
    cache.put("Rajesh", 10);
    cache.put("Rahul", 11);
    cache.put("Vivek", 12);
    Assert.assertTrue(cache.get("Rajesh") == null);
    Assert.assertTrue(cache.get("Rahul").equals(Integer.valueOf(11)));

  }

  @Test
  public void testCustomEfficientLRUCacheStrategyConfigCache() {

    Configuration<String, Integer> defaultConfiguration = new Configuration<>();
    Cache<String, Integer> cache =
        defaultConfiguration.setStrategyProvider(
            "com.supplier.impl.CustomEfficientStrategyProvider").build();
    cache.put("Rajesh", 10);
    Assert.assertTrue(cache.get("Rajesh").equals(Integer.valueOf(10)));
  }

  @Test
  public void testCustomEfficientLRUCacheStrategyWithMaxSize() {

    Configuration<String, Integer> defaultConfiguration = new Configuration<>();
    Cache<String, Integer> cache =
        defaultConfiguration.setStrategyProvider(
            "com.supplier.impl.CustomEfficientStrategyProvider").setMaxSize(2)
            .build();
    cache.put("Rajesh", 10);
    cache.put("Rahul", 11);
    cache.put("Vivek", 12);
    Assert.assertTrue(cache.get("Rajesh") == null);
    Assert.assertTrue(cache.get("Rahul").equals(Integer.valueOf(11)));

  }

}
