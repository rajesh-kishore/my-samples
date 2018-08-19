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
final class Seady implements Runnable {
	
	final private CountDownLatch startCounter;
	
	Seady(CountDownLatch startCounter) {
		this.startCounter = startCounter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		startCounter.countDown();
		ThreadLogger.logInfo("Seady current counter "+startCounter.getCount());
		

	}

}
