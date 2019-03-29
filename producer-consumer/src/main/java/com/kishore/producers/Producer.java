/**
 * 
 */
package com.kishore.producers;

import java.util.concurrent.BlockingQueue;

import com.kishore.loggers.CustomLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public final class Producer extends Thread{
	
	/**
	 * The shared queue
	 */
	private BlockingQueue<String> sharedQueue = null;
	
	
	/**
	 * The private constructor
	 * @param sharedQueue The shared queue passed by the client
	 */
	private Producer(BlockingQueue<String> sharedQueue) {
		super("ProducerThread");
		CustomLogger.writeInfoMsg("Created the Producer thread with thread name "+getName());
		this.sharedQueue = sharedQueue;
	}


	/**
	 * The static factory method to create the producer
	 * @param sharedQueue The shared queue passed by client
	 * @return The producer thread
	 */
	public static Producer createProducer(BlockingQueue<String> sharedQueue) {
		CustomLogger.writeInfoMsg("Queue with size to be assigned "+sharedQueue.size());
		return new Producer(sharedQueue);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		int i = 0;
		while (true) {
			try {
				CustomLogger.writeInfoMsg("Putting the Queue with size to be created "+sharedQueue.size());
				sharedQueue.put("Item - "+i);
				CustomLogger.writeInfoMsg("Successfully put the item - "+"Item - "+i+" , into the Queue");
			} catch (InterruptedException e) {
				CustomLogger.writeSevereMsg("Error occurred while producing the item - Item - "+i, e);
			}
			i++;
		}
	}
	
	
}
