/**
 * 
 */

package cyclicbarrier;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public final class CyclicBarrierDemo {

  /**
   * @param args
   * @throws InterruptedException
   */
  public static void main(String[] args) throws InterruptedException {
    BlockingQueue<Integer> resultList = new LinkedBlockingQueue<Integer>( 3 );
    Runnable barrierAction = new ResultBarrierAction( resultList );
    CyclicBarrier cyclicBarrier = new CyclicBarrier( 3, barrierAction );
    Runnable additionOfOneToTen =
        new AdditionOfOneToTen( cyclicBarrier, resultList );
    Runnable additionOfElevenToTwenty =
        new AdditionOfElevenToTwenty( cyclicBarrier, resultList );
    Runnable additionOfTwentyOneToThirty =
        new AdditionOfTwentyOneToThirty( cyclicBarrier, resultList );

    Thread additionOfOneToTenThread =
        new Thread( additionOfOneToTen, "additionOfOneToTen" );
    Thread additionOfElevenToTwentyThread =
        new Thread( additionOfElevenToTwenty, "additionOfElevenToTwenty" );
    Thread additionOfTwentyOneToThirtyThread =
        new Thread( additionOfTwentyOneToThirty, "additionOfTwentyOneToThirty" );

    additionOfOneToTenThread.start();
    additionOfElevenToTwentyThread.start();
    additionOfTwentyOneToThirtyThread.start();

    // additionOfOneToTenThread.join();
    additionOfElevenToTwentyThread.join();
    additionOfTwentyOneToThirtyThread.join();

    ThreadLogger.logInfo( "result done" );
  }

}
