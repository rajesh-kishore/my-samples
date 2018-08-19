/**
 * 
 */
package executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public final class CompletionServiceDemo {

	/**
	 * 
	 */
	public CompletionServiceDemo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws ExecutionException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Callable<Integer> sumTask = new SumCalculatorTask(10, 20);
		Callable<Integer> multiplicationTask = new MultiplicationCalculatorTask(10, 20);
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		/*List<Callable<Integer>> callableTasks = new ArrayList<Callable<Integer>>(2);
		callableTasks.add(multiplicationTask);
		callableTasks.add(sumTask);*/
		
		ThreadLogger.logInfo("submititng services ");
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService);
		completionService.submit(sumTask);
		completionService.submit(multiplicationTask);
		
		for (int eachTask = 0; eachTask < 2 ; eachTask++) {
			ThreadLogger.logInfo(" result is "+completionService.take().get());
		}
		
		executorService.shutdown();
	}

}
