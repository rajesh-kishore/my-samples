/**
 * 
 */
package executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class FutureTaskDemo {

	/**
	 * 
	 */
	public FutureTaskDemo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Callable<Integer> sumTask = new SumCalculatorTask(10, 20);
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(new FutureTask<Integer>(sumTask) {
		
			
			/* (non-Javadoc)
			 * @see java.util.concurrent.FutureTask#done()
			 */
			@Override
			protected void done() {
				if (isDone()) {
					
					try {
						ThreadLogger.logInfo(" the result is "+this.get());
					} catch (InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		executorService.shutdown();
		
	}

}
