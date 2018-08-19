/**
 * 
 */
package cyclicbarrier;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
final class ResultBarrierAction implements Runnable {

	final private BlockingQueue<Integer> resultList;
	
	/**
	 * 
	 */
	public ResultBarrierAction(BlockingQueue<Integer> resultList) {

		this.resultList = resultList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		ThreadLogger.logInfo("final sum being calculated ");
		Iterator<Integer> iterator = null;
		int sum = 0;
		iterator = resultList.iterator();
		while (iterator.hasNext()) {
			sum += iterator.next();
			ThreadLogger.logInfo("intermediate sum is "+sum);
		}
		
		/*for (Integer intitialSum : resultList) {
			sum += intitialSum;
			ThreadLogger.logInfo("intermediate sum is "+sum);
		}
		*/
		ThreadLogger.logInfo("sum is "+sum);

	}

}
