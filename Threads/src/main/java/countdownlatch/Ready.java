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
final class Ready implements Runnable {
	
	final private CountDownLatch startCounter;
	
	Ready(CountDownLatch startCounter) {
		this.startCounter = startCounter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startCounter.countDown();
		ThreadLogger.logInfo("Ready current counter "+startCounter.getCount());
		
	}

}
