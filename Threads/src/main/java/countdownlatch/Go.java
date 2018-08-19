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
final class Go implements Runnable {
	
	final private CountDownLatch startCounter;
	
	Go(CountDownLatch startCounter) {
		this.startCounter = startCounter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		startCounter.countDown();
		ThreadLogger.logInfo("Go current counter "+startCounter.getCount());

	}

}
