/**
 * 
 */
package countdownlatch;

import java.util.concurrent.CountDownLatch;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public final class StartRunDemo {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch startCounter = new CountDownLatch(3);
		new Thread(new Ready(startCounter),"ReadyThread").start();
		new Thread(new Seady(startCounter),"SeadyThread").start();
		new Thread(new Go(startCounter),"GoThread").start();
		ThreadLogger.logInfo("before waiting "+startCounter.getCount());
		startCounter.await();
		ThreadLogger.logInfo("After "+startCounter.getCount());
	}

}
