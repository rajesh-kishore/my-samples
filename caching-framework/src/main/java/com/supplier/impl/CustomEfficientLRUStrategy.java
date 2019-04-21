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
public class CustomEfficientLRUStrategy<K, V> implements CacheStrategy<K, V> {

  private int maxSize = 10;

  private final Map<K, BucketVal<K, V>> map =
      new HashMap<>();

  private NavigableCacheable<K, V> head;

  private NavigableCacheable<K, V> tail;

  private int curSize = 0;

  private static class BucketVal<K, V> {
    private Map<K, NavigableCacheable<K, V>> map = new HashMap<>();

    void put(NavigableCacheable<K, V> cacheable) {
      map.put(cacheable.key(), cacheable);
    }

    NavigableCacheable<K, V> get(K key) {
      return map.get(key);
    }

    void remove(K key) {
      map.remove(key);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.kishore.caching.core.extensions.CacheStrategy#put(com.kishore.caching
   * .api.NavigableCacheable)
   */
  @Override
  public void put(NavigableCacheable<K, V> value) {
    if ( curSize + 1 > maxSize ) {
      evict();
    }
    BucketVal<K, V> bucketVal =
        map.getOrDefault(value.key(), new BucketVal<K, V>());
    map.put(value.key(), bucketVal);
    bucketVal.put(value);
    moveToTop(value);
    curSize++;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.kishore.caching.core.extensions.CacheStrategy#get(java.lang.Object)
   */
  @Override
  public NavigableCacheable<K, V> get(K key) {
    BucketVal<K, V> bucketVal = map.get(key);
    if ( bucketVal == null ) {
      return null;
    }
    NavigableCacheable<K, V> val = bucketVal.get(key);
    if ( val != null ) {
      moveToTop(val);
    }
    return val;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.caching.core.extensions.CacheStrategy#maxSize(int)
   */
  @Override
  public void maxSize(int maxSize) {
    this.maxSize = maxSize;
  }

  private void evict() {
    removeLast();
  }

  private void removeLast() {
    BucketVal<K, V> bucketVal = map.get(tail.key());
    bucketVal.remove(tail.key());

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
