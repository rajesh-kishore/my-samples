/**
 * 
 */

package com.kishore.caching.api;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 * @param <K> The key data type
 * @param <V> The value data type
 */
public interface Cacheable<K, V> {

  /**
   * Returns the key.
   * 
   * @return The Key
   */
  public K key();

  /**
   * Returns the value
   * 
   * @return The value
   */
  public V value();

}
