/**
 * 
 */

package com.kishore.caching.api;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
final class WrappedCacheableValue<K, V> implements NavigableCacheable<K, V> {

  final private K key;

  final private V value;

  private NavigableCacheable<K, V> after;

  private NavigableCacheable<K, V> before;

  WrappedCacheableValue(K key, V value) {
    this.key = key;
    this.value = value;
    after = before = null;
  }

  @Override
  public K key() {
    return key;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.caching.core.Cacheable#value()
   */
  @Override
  public V value() {
    return value;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.caching.api.NavigableCacheable#after()
   */
  @Override
  public NavigableCacheable<K, V> after() {
    return after;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.caching.api.NavigableCacheable#after(com.kishore.caching
   * .engine.NavigableCacheable)
   */
  @Override
  public void after(NavigableCacheable<K, V> cacheable) {
    this.after = cacheable;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.caching.api.NavigableCacheable#before()
   */
  @Override
  public NavigableCacheable<K, V> before() {
    return before;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.caching.api.NavigableCacheable#before(com.kishore.caching
   * .engine.NavigableCacheable)
   */
  @Override
  public void before(NavigableCacheable<K, V> cacheable) {
    this.before = cacheable;
  }
}
