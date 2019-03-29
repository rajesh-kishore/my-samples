/**
 * 
 */
package com.kishore.loggers;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class CustomLogger {
	
	private static Logger logger = Logger.getLogger(CustomLogger.class.getName());
	
	
	/**
	 * Writes the finest level messages
	 * @param message
	 */
	public static void writeFinestMsg(String message) {
		logger.log(Level.FINEST, message);
	}
	
	
	/**
	 * Writes the severe message
	 * @param message The message to be written
	 * @param throwable The exception to be thrown
	 */
	public static void writeSevereMsg(String message,Throwable throwable) {
		logger.log(Level.SEVERE, message,throwable);
	}
	
	
	/**
	 * Writes the info level message
	 * @param message
	 */
	public static void writeInfoMsg(String message) {
		logger.log(Level.INFO, message);
	}
	
	/**
	 * Writes the warning level message
	 * @param message
	 */
	public static void writeWarningMsg(String message) {
		logger.log(Level.WARNING, message);
	}
}
