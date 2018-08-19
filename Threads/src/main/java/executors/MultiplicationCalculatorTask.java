/**
 * 
 */
package executors;

import java.util.concurrent.Callable;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
final class MultiplicationCalculatorTask implements Callable<Integer> {


	private int operandOne;
	
	private int operandTwo;
	
	/**
	 * 
	 */
	public MultiplicationCalculatorTask(int operandOne,int operandTwo) {
		this.operandOne = operandOne;
		this.operandTwo = operandTwo;
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Integer call() throws Exception {
		ThreadLogger.logInfo("inside class "+this.getClass().getSimpleName()+", thread name is "+Thread.currentThread().getName());
		return operandOne * operandTwo;
	}



}
