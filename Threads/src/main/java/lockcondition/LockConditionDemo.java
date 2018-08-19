/**
 * 
 */
package lockcondition;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
final class LockConditionDemo {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {

		CoinMachine coinMachine = CoinMachine.getInstance();
		CoinProducer coinProducer = new CoinProducer(coinMachine);
		Thread coinProducerThread1 = new Thread(coinProducer,"coinProducerThread1");
		coinProducer = new CoinProducer(coinMachine);
		Thread coinProducerThread2 = new Thread(coinProducer,"coinProducerThread2");
		coinProducer = new CoinProducer(coinMachine);
		Thread coinProducerThread3 = new Thread(coinProducer,"coinProducerThread3");
		
		coinProducerThread1.start();
		coinProducerThread2.start();
		coinProducerThread3.start();
		
		
		CoinConsumer coinConsumer = new CoinConsumer(coinMachine);
		Thread coinConsumerThread1 = new Thread(coinConsumer,"coinConsumerThread1");
		coinConsumer = new CoinConsumer(coinMachine);
		Thread coinConsumerThread2 = new Thread(coinConsumer,"coinConsumerThread2");
		
		coinConsumerThread1.start();
		coinConsumerThread2.start();
		
		coinProducerThread1.join();
		coinProducerThread2.join();
		coinProducerThread3.join();
		
		
		coinConsumerThread1.join();
		coinConsumerThread2.join();
	}

}

