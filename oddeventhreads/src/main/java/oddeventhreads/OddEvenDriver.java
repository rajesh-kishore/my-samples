/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package oddeventhreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class OddEvenDriver {

  private static AtomicBoolean oddKicker = new AtomicBoolean(true);

  /**
   * @param args
   */
  public static void main(String[] args) {

    final int times = 10;
    final Semaphore semaphore = new Semaphore(1, true);
    final SharedPrinter printer = new SharedPrinter();
    final Generator oddPrinter =
        new OddGenerator(semaphore, times, oddKicker, printer);
    final Generator evenPrinter =
        new EvenGenerator(semaphore, times, oddKicker, printer);

    ExecutorService service = Executors.newFixedThreadPool(2);
    service.execute(evenPrinter);
    service.execute(oddPrinter);

    service.shutdown();
  }

}
