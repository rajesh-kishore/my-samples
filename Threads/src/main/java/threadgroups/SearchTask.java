/**
 * 
 */
package threadgroups;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class SearchTask implements Runnable {
	
	private Result result = null;

	/**
	 * 
	 */
	public SearchTask(Result result) {
		result = result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		String name=Thread.currentThread().getName();
		ThreadLogger.logInfo(String.format("Thread %s: Start\n",name));
	    try {
	      doTask();
	      result.setName(name);
	    } catch (InterruptedException e) {
	    	ThreadLogger.logInfo(String.format("Thread %s: Interrupted\n",name));
	      return;
	    }
	    ThreadLogger.logInfo(String.format("Thread %s: End\n",name));
	}
	
	
	private void doTask() throws InterruptedException {
	    Random random=new Random((new Date()).getTime());
	    int value=(int)(random.nextDouble()*100);
	    ThreadLogger.logInfo(String.format("Thread %s: %d\n",Thread.currentThread().getName(),value));
	    TimeUnit.SECONDS.sleep(value);
	  }

}
