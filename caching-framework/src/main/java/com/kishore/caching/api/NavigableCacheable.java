/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.caching.api;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public interface NavigableCacheable<K, V> extends Cacheable<K, V> {

  NavigableCacheable<K, V> after();

  void after(NavigableCacheable<K, V> cacheable);

  NavigableCacheable<K, V> before();

  void before(NavigableCacheable<K, V> cacheable);

}
