/**
 * 
 */
package lockcondition;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
final class CoinMachine {
	
	private final static int max_size = 50;
	
	private Queue<Integer> coinList = new LinkedList<Integer>();
	
	private Lock machineLock = new ReentrantLock();
	
	private Condition insertLockCondition = machineLock.newCondition();
	
	private Condition withdrawLockCondition = machineLock.newCondition();
	
	
	private CoinMachine() {
	}
	
	
	private static class CoinMachineSingletonCreator {
		/**
		 *  The static private CoinMachine
		 */
		private static CoinMachine coinMachine = new CoinMachine();
	}
	
	
	public static CoinMachine getInstance() {
		return CoinMachineSingletonCreator.coinMachine;
	}
	
	public void insertCoin(int coin) {
		machineLock.lock();
		try {
				ThreadLogger.logInfo("lock acquired insertCoin");
				Thread.sleep(10000);
				while (coinList.size() == max_size) {
					insertLockCondition.await();
				}
				coinList.offer(coin);
				this.withdrawLockCondition.signalAll();	
		} catch (InterruptedException e) {
			
		} finally {
			machineLock.unlock();
		}
	}
	
	public int withdrawCoin() {
		int currentCoin= 0;
		machineLock.lock();
		try {
			ThreadLogger.logInfo("lock acquired withdrawCoin");
			Thread.sleep(10000);
			 while (coinList.isEmpty()) {
				 this.withdrawLockCondition.await();
			 }
			currentCoin = coinList.poll();
			
			this.insertLockCondition.signalAll();
		} catch( InterruptedException e) {
			
		} finally {
			machineLock.unlock();
		}
		
		return currentCoin;
	}

}
