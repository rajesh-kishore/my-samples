/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.consumers;

import com.kishore.shared.SharedLock;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public abstract class Consumer {

  protected final SharedLock lock;

  private final int[] numbers;

  private final int remainderIndicator;

  protected Consumer(SharedLock lock, int[] numbers, int remainderIndicator) {
    this.lock = lock;
    this.numbers = numbers;
    this.remainderIndicator = remainderIndicator;
  }

  protected void consume() {
    synchronized ( lock ) {
      if ( numbers[lock.getIndex()] % 2 != remainderIndicator ) {
        try {
          lock.wait();
        } catch ( InterruptedException e ) {
          e.printStackTrace();
        }
      } else {
        System.out.println(Thread.currentThread().getName() + " [ "
            + (remainderIndicator == 0 ? "Even" : "Odd") + "] "
            + numbers[lock.getIndex()]);
        lock.incrementIndex(numbers);
        lock.notifyAll();
      }
    }
  }
}
