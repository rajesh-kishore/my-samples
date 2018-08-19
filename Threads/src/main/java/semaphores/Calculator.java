/**
 * 
 */
package semaphores;

import java.util.concurrent.Semaphore;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
final class Calculator implements Runnable {

	final private SumOperation sumOperation;
	final private Semaphore semaphore;
	
	/**
	 * @param semaphore TODO
	 * 
	 */
	public Calculator(SumOperation sumOperation, Semaphore semaphore ) {
		this.sumOperation = sumOperation;
		this.semaphore = semaphore;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		ThreadLogger.logInfo("Cuurent thread is "+Thread.currentThread().getName()+ ", available permits are "+semaphore.availablePermits());
		try {
			
			semaphore.acquire(2);
			ThreadLogger.logInfo("Executing "+Thread.currentThread().getName()+" available permits are "+semaphore.availablePermits()+", sum is "+sumOperation.getOperandFirst()+sumOperation.getOperandSecond());
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaphore.release(2);
		}
		
	}

}
