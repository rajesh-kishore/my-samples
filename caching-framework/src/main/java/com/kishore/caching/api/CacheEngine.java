/**
 * 
 */

package com.kishore.caching.api;

import com.kishore.caching.core.extensions.CacheStrategy;
import com.kishore.caching.core.extensions.spi.StrategyProvider;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
final class CacheEngine<K, V> {

  private final CacheStrategy<K, V> strategy;

  /**
   * @throws ClassNotFoundException
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  private CacheEngine(Configuration<K, V> configuration)
      throws InstantiationException, IllegalAccessException,
      ClassNotFoundException {
    @SuppressWarnings( "unchecked" )
    final StrategyProvider<K, V> strategyProvider = (StrategyProvider<K, V>)
        Class.forName(configuration.getStrategyProvider()).newInstance();
    strategy = strategyProvider.getCacheStrategy();
    strategy.maxSize(configuration.getMaxSize());
    this.cache = new Cache<K, V>(this);
  }

  private Cache<K, V> cache = null;

  public static <K, V> CacheEngine<K, V> getCacheEngine(
      Configuration<K, V> configuration) {

    CacheEngine<K, V> cacheEngine = null;
    try {
      cacheEngine = new CacheEngine<K, V>(configuration);
    } catch ( InstantiationException e ) {
      e.printStackTrace();
    } catch ( IllegalAccessException e ) {
      e.printStackTrace();
    } catch ( ClassNotFoundException e ) {
      e.printStackTrace();
    }
    return cacheEngine;
  }

  /**
   * Gets the cache instance.
   *
   * @return The cache instance.
   */
  final Cache<K, V> getCacheInstance() {
    return cache;
  }

  final void put(K key, V value) {
    strategy.put(new WrappedCacheableValue<K, V>(key, value));
  }

  final V get(K key) {
    Cacheable<K, V> wrappedCacheableValue = strategy.get(key);
    return wrappedCacheableValue == null ? null : wrappedCacheableValue.value();
  }
}
