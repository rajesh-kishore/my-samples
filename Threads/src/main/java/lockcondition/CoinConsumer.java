/**
 * 
 */
package lockcondition;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
class CoinConsumer implements Runnable {

	private CoinMachine coinMachine;
	
	/**
	 * 
	 */
	public CoinConsumer(CoinMachine coinMachine) {
		this.coinMachine = coinMachine;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			ThreadLogger.logInfo("withdrawn coin "+coinMachine.withdrawCoin());
		}
	}

}
