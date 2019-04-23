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
public final class EvenConsumer extends Consumer implements Runnable {

  /**
   * @param lock
   * @param numbers
   */
  public EvenConsumer(SharedLock lock, int[] numbers, int remainderIndicator) {
    super(lock, numbers, remainderIndicator);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
    while ( !lock.isShutDownInitiated() ) {
      consume();
    }
  }
}
