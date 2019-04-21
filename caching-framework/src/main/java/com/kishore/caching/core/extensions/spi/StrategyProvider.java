/**
 * 
 */

package com.kishore.caching.core.extensions.spi;

import com.kishore.caching.core.extensions.CacheStrategy;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @param <V>
 * @param <K>
 * @since V1
 */
public interface StrategyProvider<K, V> {

  public CacheStrategy<K, V> getCacheStrategy();
}
