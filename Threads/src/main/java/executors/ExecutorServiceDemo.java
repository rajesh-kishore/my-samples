/**
 * 
 */

package executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public final class ExecutorServiceDemo {

  /**
	 * 
	 */
  public ExecutorServiceDemo() {
  }

  /**
   * @param args
   * @throws InterruptedException
   * @throws ExecutionException
   */
  public static void main(String[] args) throws InterruptedException,
      ExecutionException {

    Callable<Integer> sumTask = new SumCalculatorTask( 10, 20 );
    Callable<Integer> multiplicationTask =
        new MultiplicationCalculatorTask( 10, 20 );
    // ExecutorService executorService = Executors.newCachedThreadPool();

    ExecutorService executorService =
        Executors.newFixedThreadPool( 2, new ThreadFactory() {
          int counter = 0;

          @Override
          public Thread newThread(Runnable r) {

            return new Thread( r, "sample " + counter++ );
          }
        } );
    // ExecutorService executorService = Executors.newScheduledThreadPool(2);

    List<Callable<Integer>> callableTasks =
        new ArrayList<Callable<Integer>>( 2 );
    callableTasks.add( multiplicationTask );
    callableTasks.add( sumTask );
    callableTasks.add( new MultiplicationCalculatorTask( 110, 20 ) );
    callableTasks.add( new SumCalculatorTask( 20, 30 ) );
    callableTasks.add( new MultiplicationCalculatorTask( 110, 20 ) );
    callableTasks.add( new MultiplicationCalculatorTask( 50, 20 ) );

    ThreadLogger.logInfo( "submititng services " );
    List<Future<Integer>> futureResults =
        executorService.invokeAll( callableTasks );
    ThreadLogger.logInfo( "submitted services " );
    for ( Future<Integer> futureObj : futureResults ) {
      ThreadLogger.logInfo( "future obj is " + futureObj.toString()
          + " and result is " + futureObj.get() );
    }
    ThreadLogger.logInfo( "calcl done " );
    executorService.shutdown();
  }

}
