/**
 * 
 */
package semaphores;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
final class SumOperation {
	
	final private int operandFirst;
	
	final private int operandSecond;

	/**
	 * 
	 */
	public SumOperation(int operandFirst, int operandSecond) {
		this.operandFirst = operandFirst;
		this.operandSecond = operandSecond;
	}

	/**
	 * @return the operandFirst
	 */
	final int getOperandFirst() {
		return operandFirst;
	}

	/**
	 * @return the operandSecond
	 */
	final int getOperandSecond() {
		return operandSecond;
	}

}
