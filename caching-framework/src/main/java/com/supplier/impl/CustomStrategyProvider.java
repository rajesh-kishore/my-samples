/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.supplier.impl;

import com.kishore.caching.core.extensions.CacheStrategy;
import com.kishore.caching.core.extensions.spi.StrategyProvider;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class CustomStrategyProvider<K, V> implements
    StrategyProvider<K, V> {

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.caching.core.spi.StrategyProvider#getCacheStrategy()
   */
  @Override
  public CacheStrategy<K, V> getCacheStrategy() {
    return new CustomLRUCacheStrategyImpl<>();
  }

}
