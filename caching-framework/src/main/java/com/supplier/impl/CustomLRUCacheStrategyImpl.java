/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.supplier.impl;

import java.util.HashMap;
import java.util.Map;

import com.kishore.caching.api.NavigableCacheable;
import com.kishore.caching.core.extensions.CacheStrategy;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
final class CustomLRUCacheStrategyImpl<K, V> implements CacheStrategy<K, V> {

  private int maxSize = 10;

  private final Map<K, NavigableCacheable<K, V>> map =
      new HashMap<>();

  private NavigableCacheable<K, V> head;

  private NavigableCacheable<K, V> tail;

  private int curSize = 0;

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.kishore.caching.core.CacheStrategy#put(com.kishore.caching.api.Cacheable
   * )
   */
  @Override
  public void put(NavigableCacheable<K, V> value) {
    if ( curSize + 1 > maxSize ) {
      evict();
    }
    map.put(value.key(), value);
    moveToTop(value);
    curSize++;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.caching.core.CacheStrategy#get(java.lang.Object)
   */
  @Override
  public NavigableCacheable<K, V> get(K key) {
    NavigableCacheable<K, V> val = map.get(key);
    if ( val != null ) {
      moveToTop(val);
    }
    return val;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.caching.core.CacheStrategy#maxSize(int)
   */
  @Override
  public void maxSize(int maxSize) {
    this.maxSize = maxSize;
  }

  private void evict() {
    removeLast();
  }

  private void removeLast() {
    map.remove(tail.key());
    if ( tail.before() != null ) {
      tail.before().after(null);
    }
    tail = tail.before();
    curSize--;
  }

  private void moveToTop(NavigableCacheable<K, V> curNode) {
    if ( head == null ) {
      head = tail = curNode;
    } else {
      if ( curNode == tail ) {
        tail = curNode.before();
        if ( tail != null ) {
          tail.after(null);
        }
      }
      curNode.after(head);
      head.before(curNode);
      head = curNode;
    }
  }

}
