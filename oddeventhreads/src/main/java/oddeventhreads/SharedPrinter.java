/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package oddeventhreads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class SharedPrinter {

  private boolean isOddPritned = false;

  public void print(Semaphore semaphore, int number, AtomicBoolean oddKicker) {

    try {
      isOddPritned = true;
      semaphore.acquire();
      oddKicker.set(false);
      System.out.println(number);
    } catch ( InterruptedException e ) {
      e.printStackTrace();
    } finally {
      semaphore.release();
    }
  }

}
