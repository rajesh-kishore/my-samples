/**
 * 
 */
package executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public final class ScheduledExecutorSeviceDemo {

	/**
	 * 
	 */
	public ScheduledExecutorSeviceDemo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Callable<Integer> sumTask = new SumCalculatorTask(10, 20);
		Callable<Integer> multiplicationTask = new MultiplicationCalculatorTask(10, 20);
		ScheduledExecutorService executorService = null;
		executorService = Executors.newScheduledThreadPool(2);
		List<Callable<Integer>> callableTasks = new ArrayList<Callable<Integer>>(2);
		callableTasks.add(multiplicationTask);
		callableTasks.add(sumTask);

		ScheduledFuture<Integer> scheduledFutureSum = executorService.schedule(sumTask, 2,TimeUnit.SECONDS);
		ScheduledFuture<Integer> scheduledFutureMult = executorService.schedule(multiplicationTask, 1,TimeUnit.SECONDS);
		
		ThreadLogger.logInfo(" multiplication is "+scheduledFutureMult.get() );
		ThreadLogger.logInfo(" sum is "+scheduledFutureSum.get() );
		
		executorService.shutdown();
		
		
		executorService = Executors.newScheduledThreadPool(2);
		
		executorService.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				ThreadLogger.logInfo(" testing , threa is "+ Thread.currentThread().getName());				
			}
			
		}, 1, 2, TimeUnit.SECONDS);
		
		executorService.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				ThreadLogger.logInfo(" testing 2, threa is "+ Thread.currentThread().getName());				
			}
			
		}, 0, 1, TimeUnit.SECONDS);
		
		executorService.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				ThreadLogger.logInfo(" testing 3, threa is "+ Thread.currentThread().getName());				
			}
			
		}, 0, 3, TimeUnit.SECONDS);
	}

}
