/**
 * 
 */
package customthreadgroups;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
class CustomThreadGroup extends ThreadGroup {

	/**
	 * @param name
	 */
	public CustomThreadGroup(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see java.lang.ThreadGroup#uncaughtException(java.lang.Thread, java.lang.Throwable)
	 */
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		ThreadLogger.logInfo(String.format("The thread %s has thrown an Exception\n",t.getName()));
		ThreadLogger.logInfo("stacktrace is "+e.getMessage());
	}

}
