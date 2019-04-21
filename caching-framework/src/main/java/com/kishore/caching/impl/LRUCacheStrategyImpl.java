/**
 * 
 */

package com.kishore.caching.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.kishore.caching.api.NavigableCacheable;
import com.kishore.caching.core.extensions.CacheStrategy;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @param <V>
 * @param <K>
 * @since V1
 */
final class LRUCacheStrategyImpl<K, V> implements
    CacheStrategy<K, V> {

  /**
   * The max number of entries allowed.
   */
  private int maxItems = 10;
  /**
   * The internal storage of map.
   */
  final private Map<K, NavigableCacheable<K, V>> cacheMap;

  public LRUCacheStrategyImpl() {
    cacheMap =
        new LinkedHashMap<K, NavigableCacheable<K, V>>(maxItems,
            .75f, false) {
          /**
               * 
               */
          private static final long serialVersionUID = 1L;

          /*
           * (non-Javadoc)
           * 
           * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
           */
          @Override
          protected boolean removeEldestEntry(
              java.util.Map.Entry<K, NavigableCacheable<K, V>> eldest) {
            return size() > maxItems;
          }
        };
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.kishore.caching.core.strategy.CacheStrategy#put(com.kishore.caching
   * .core.Cacheable)
   */
  @Override
  public void put(NavigableCacheable<K, V> value) {
    cacheMap.put(value.key(), value);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.caching.core.strategy.CacheStrategy#get(java.lang.Object)
   */
  @Override
  public NavigableCacheable<K, V> get(K key) {
    return cacheMap.get(key);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.caching.core.CacheStrategy#maxSize(int)
   */
  @Override
  public void maxSize(int maxSize) {
    maxItems = maxSize;
  }

}
