/**
 * 
 */
package com.kishore.trace.logging.loggers;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The JUL wrapper class which is repsonsibe for logging the messages, the wrapper class has been used because in future if some other logger has to be introduced then that can be introduced with the code
 * change in client classes.
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public final class TraceAgentLogger {
	
	
		private static TraceAgentLogger traceAgentLogger = new TraceAgentLogger();
		
		
		public static TraceAgentLogger getTraceAgentLoggerInstance() {
			return traceAgentLogger;
		}
	
		/**
		 * 
		 */
		private TraceAgentLogger() {

		}
		
		/**
		 * 
		 */
		private Logger logger = Logger.getLogger(TraceAgentLogger.class.getName());;
		
		
		/**
		 * @return
		 */
		private Logger getInstance() {
			return logger;
		}
		
		/**
		 * @param message
		 */
		public  void logInfo(String message) {
			getInstance().info(message);
		}
		
		
		/**
		 * @param message
		 */
		public  void logFinest(String message) {
			getInstance().log(Level.FINEST,message);
		}
		
		

		/**
		 * @param message
		 * @param throwable
		 */
		public void logError(String message,Throwable throwable) {
			getInstance().log(Level.SEVERE, message,throwable);
		}
		

}

