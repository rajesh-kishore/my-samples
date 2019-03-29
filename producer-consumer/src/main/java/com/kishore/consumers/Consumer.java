/**
 * 
 */
package com.kishore.consumers;

import java.util.concurrent.BlockingQueue;

import com.kishore.loggers.CustomLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class Consumer extends Thread {

	/**
	 * The shared queue
	 */
	private BlockingQueue<String> sharedQueue = null;
	
	/**
	 * 
	 */
	private Consumer(BlockingQueue<String> sharedQueue) {
		super("ConsumerThread");
		this.sharedQueue = sharedQueue;
	}
	
	public static Consumer createConsumer(BlockingQueue<String> sharedQueue) {
		CustomLogger.writeInfoMsg("Queue with size to be assigned "+sharedQueue.size());
		return new Consumer(sharedQueue);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (true) {
			try {
				CustomLogger.writeInfoMsg("Preparing to consume the item");
				String item = sharedQueue.take();
				CustomLogger.writeInfoMsg("Successfully consumed item - "+item+" from the Queue");
			} catch (InterruptedException e) {
				CustomLogger.writeSevereMsg("Error occurred while consuming the item .", e);
			}
		}
	}

}
