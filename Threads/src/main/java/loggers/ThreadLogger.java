/**
 * 
 */
package loggers;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class ThreadLogger {
	
	private static Logger javaLogger = Logger.getLogger("generalthreadlogger");  
	static {
		//javaLogger.addHandler(new ConsoleHandler());
		javaLogger.setLevel(Level.ALL);
	}
	
	
	public static void logInfo(String msg) {
		javaLogger.info(msg);
	}

}
