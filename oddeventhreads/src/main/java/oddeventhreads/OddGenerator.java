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
public final class OddGenerator extends Generator {

  public OddGenerator(Semaphore semaphore, int times, AtomicBoolean oddKicker,
      SharedPrinter printer) {
    super(semaphore, times, oddKicker, printer);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Runnable#run()
   */
  public void run() {

    while ( oddKicker.get() == false )
      ;

    super.generate(1);
  }
}
