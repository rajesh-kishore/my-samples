/**
 * 
 */

package com.kishore.caching.api;

/**
 * This is the public facing api.
 *
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 * @param <K> The key data type
 * @param <V> The value data typeK,
 */
public final class Cache<K, V> {

  private final CacheEngine<K, V> cacheEngine;

  public Cache(CacheEngine<K, V> cacheEngine) {
    this.cacheEngine = cacheEngine;
  }

  public final void put(K key, V value) {
    cacheEngine.put(key, value);
  }

  public final V get(K key) {
    return cacheEngine.get(key);
  }
}
