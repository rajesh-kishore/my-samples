/**
 * 
 */
package customthreadgroups;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
 class CustomThreadGroupDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CustomThreadGroup customThreadGroup = new CustomThreadGroup("customthreadgroupdemo");
		new Thread(customThreadGroup,new Runnable() {
				@Override
				public void run() {
	
					throw new RuntimeException("Intended exception");
				}
		},"customThreadResultsinException").start();
	}

}
