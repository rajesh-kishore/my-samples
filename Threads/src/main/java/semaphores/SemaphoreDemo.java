/**
 * 
 */
package semaphores;

import java.util.concurrent.Semaphore;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public final class SemaphoreDemo {

	/**
	 * 
	 */
	public SemaphoreDemo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Semaphore semaphore = new Semaphore(2);

		Thread threadCalculator1 = new Thread(new Calculator(new SumOperation(1, 2), semaphore),"threadCalculator1");
		Thread threadCalculator2 = new Thread(new Calculator(new SumOperation(4, 5), semaphore),"threadCalculator2");
		Thread threadCalculator3 = new Thread(new Calculator(new SumOperation(8, 7), semaphore),"threadCalculator3");
		threadCalculator1.start();
		threadCalculator2.start();
		threadCalculator3.start();
		
	}

}
