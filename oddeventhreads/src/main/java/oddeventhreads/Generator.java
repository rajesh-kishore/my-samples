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
public abstract class Generator implements Runnable {

  protected final Semaphore semaphore;

  protected final int times;

  protected final AtomicBoolean oddKicker;

  protected final SharedPrinter printer;

  public Generator(Semaphore semaphore, int times, AtomicBoolean oddKicker,
      SharedPrinter printer) {
    this.semaphore = semaphore;
    this.times = times;
    this.oddKicker = oddKicker;
    this.printer = printer;
  }

  final void generate(int startNumber) {

    int number = startNumber;
    int i = 0;
    while ( i < times ) {
      /*
       * if ( startNumber % 2 == 0 && i != 19 ) {
       * 
       * }
       */
      printer.print(semaphore, number, oddKicker);
      i++;
      number += 2;
    }
  }

}
