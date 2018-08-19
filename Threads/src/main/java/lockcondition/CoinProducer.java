/**
 * 
 */
package lockcondition;

import java.util.Random;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
class CoinProducer implements Runnable {
	
	
	private CoinMachine coinMachine;
	/**
	 * 
	 */
	public CoinProducer(CoinMachine coinMachine) {
		this.coinMachine = coinMachine;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Random random = new Random(10);
		while (true) {
			int coinToBeInserted = random.nextInt();
			coinMachine.insertCoin(coinToBeInserted);
			ThreadLogger.logInfo("inseted coin  "+coinToBeInserted);	
		}
	}

}
