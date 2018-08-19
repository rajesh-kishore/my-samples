/**
 * 
 */

package cyclicbarrier;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
final class AdditionOfElevenToTwenty implements Runnable {

  final private CyclicBarrier cyclicBarrier;

  final private BlockingQueue<Integer> resultList;

  /**
	 * 
	 */
  public AdditionOfElevenToTwenty( CyclicBarrier cyclicBarrier,
      BlockingQueue<Integer> resultList ) {
    this.cyclicBarrier = cyclicBarrier;
    this.resultList = resultList;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {

    int sum = 0;
    for ( int number = 11; number <= 20; number++ ) {
      sum += number;
    }
    try {
      resultList.put( sum );
    } catch ( InterruptedException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    ThreadLogger.logInfo( Thread.currentThread().getName() + " finised sum" );
    try {
      cyclicBarrier.await();
    } catch ( InterruptedException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( BrokenBarrierException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
