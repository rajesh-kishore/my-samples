/**
 * 
 */

package com.kishore.caching.api;

/**
 * This is primary consumer facing class. <code>
 * Configuration<String,Integer> configuration = new Configuration<String,Integer>();
   Cache<String,Integer> cache = configuration.setMaxSize(2).setStrategyProvider("fully.qualified.classname").build();
 * or
 * Cache<String,Integer> cache = configuration.build();
 *           
 * </code
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class Configuration<K, V> {

  private int maxSize = 10;

  private String strategyProvider =
      "com.kishore.caching.impl.DefaultStrategyProvider";

  public Configuration<K, V> setMaxSize(int maxSize) {
    this.maxSize = maxSize;
    return this;
  }

  public Configuration<K, V> setStrategyProvider(String strategyProvider) {
    this.strategyProvider = strategyProvider;
    return this;
  }

  public Cache<K, V> build() {
    return CacheEngine.getCacheEngine(this).getCacheInstance();
  }

  /**
   * @return the maxSize
   */
  public final int getMaxSize() {
    return maxSize;
  }

  /**
   * @return the strategyProvider
   */
  public final String getStrategyProvider() {
    return strategyProvider;
  }

}
