/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.clients;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.kishore.consumers.EvenConsumer;
import com.kishore.consumers.OddConsumer;
import com.kishore.shared.SharedLock;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class Client {

  /**
   * @param args
   */
  public static void main(String[] args) {

    int numbers[] = { 11, 2, 1, 45, 46, 24, 26, 28, 32, 31 };

    final SharedLock lock = new SharedLock();
    ExecutorService service = Executors.newFixedThreadPool(3);
    service
        .execute(new OddConsumer(lock, numbers, 1));
    service
        .execute(new OddConsumer(lock, numbers, 1));

    service
        .execute(new EvenConsumer(lock, numbers, 0));

    service.shutdown();
  }

}
