/**
 * 
 */
package com.kishore.clients;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.LogManager;

import com.kishore.consumers.Consumer;
import com.kishore.loggers.CustomLogger;
import com.kishore.producers.Producer;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class ProducerConsumerClient {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	public static void main(String[] args) throws SecurityException, IOException {
		LogManager.getLogManager().readConfiguration(ProducerConsumerClient.class.getResourceAsStream("/producer-consumer-logging.properties"));
		BlockingQueue<String> sharedQueue = new ArrayBlockingQueue<String>(10);
		CustomLogger.writeInfoMsg("Created queue with size "+sharedQueue.size());
		
		Thread mainThread = Thread.currentThread();
		mainThread.setName("ProducerConsumerClient Thread");
		
		CustomLogger.writeInfoMsg("main thread name - "+Thread.currentThread().getName());
		
		Producer producer = Producer.createProducer(sharedQueue);
		Consumer consumer = Consumer.createConsumer(sharedQueue); 
		
		producer.start();
		consumer.start();
		
	/*	try {
			producer.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			consumer.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
