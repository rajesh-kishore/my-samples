/**
 * 
 */

package com.kishore.caching.core.extensions;

import com.kishore.caching.api.NavigableCacheable;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @param <K>
 * @since V1
 */
public interface CacheStrategy<K, V> {

  public void put(NavigableCacheable<K, V> value);

  public NavigableCacheable<K, V> get(K key);

  public void maxSize(int maxSize);

}
