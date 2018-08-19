/**
 * 
 */
package threadgroups;

import java.util.concurrent.TimeUnit;

import loggers.ThreadLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class ThreadGroupDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ThreadGroup threadGroup = new ThreadGroup("Searcher");
		Result result = new Result();
		SearchTask searchTask=new SearchTask(result);
		for (int i=0; i<5; i++) {
		      Thread thread=new Thread(threadGroup, searchTask,"searchtaskthread"+i);
		      thread.start();
		      try {
		        TimeUnit.SECONDS.sleep(1);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		}
		listThreadGroupInfo(threadGroup);
		waitFinish(threadGroup);
		threadGroup.interrupt();
	}
	
	
	private static void listThreadGroupInfo(ThreadGroup threadGroup) {
		ThreadLogger.logInfo(String.format("Number of Threads: %d\n",threadGroup.activeCount()));
		//System.out.printf("Number of Threads: %d\n",threadGroup.activeCount());
		ThreadLogger.logInfo("Information about the Thread Group\n");
	    threadGroup.list();
	    Thread[] threads=new Thread[threadGroup.activeCount()];
	    threadGroup.enumerate(threads);
	    for (int i=0; i<threadGroup.activeCount(); i++) {
	    	ThreadLogger.logInfo(String.format("Thread %s: %s\n",threads[i].getName(),threads[i].getState()));
	    }
	}
	
	private static void waitFinish(ThreadGroup threadGroup) {
	    while (threadGroup.activeCount()>9) {
	      try {
	        TimeUnit.SECONDS.sleep(1);
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	    }
	  }

}
