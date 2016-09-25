/**
 * 
 */

package com.kishore.trace.logging.agents;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.logging.LogManager;

import com.kishore.trace.logging.transformers.InjectLoggingClassFileTransformer;

/**
 * This is the premain class , a requisite for a java agent. This would be
 * called before main class
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public final class TraceLoggingAgent {

  /**
   * The premain class.
   * 
   * @param agentArgs The agent args
   * @param inst The instrumentation object
   * @throws SecurityException Security Exception
   * @throws IOException The inputoutput exception.
   */
  public static void premain(String agentArgs, Instrumentation inst)
      throws SecurityException, IOException {
    LogManager.getLogManager().readConfiguration(
        TraceLoggingAgent.class
            .getResourceAsStream( "/trace-logging.properties" ) );
    inst.addTransformer( new InjectLoggingClassFileTransformer() );
  }
}
