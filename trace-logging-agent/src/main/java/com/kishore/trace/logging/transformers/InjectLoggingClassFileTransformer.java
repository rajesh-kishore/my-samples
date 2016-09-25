/**
 * 
 */
package com.kishore.trace.logging.transformers;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * This is the class which actually modifies the target classes , in this case
 * its modifying the target classes code to introduce logger message before call
 * of each methods
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class InjectLoggingClassFileTransformer implements ClassFileTransformer {

  /**
   * The private member which is used to ignore the classes for instrumentation
   */
  private String[] ignore = new String[] { "sun/", "com/sun/", "java/",
      "javax/", "com/kishore/trace/" };

  /*
   * (non-Javadoc)
   * 
   * @see
   * java.lang.instrument.ClassFileTransformer#transform(java.lang.ClassLoader,
   * java.lang.String, java.lang.Class, java.security.ProtectionDomain, byte[])
   */
  @Override
  public byte[] transform(ClassLoader loader, String className,
      Class classBeingRedefined, ProtectionDomain protectionDomain,
      byte[] classfileBuffer) throws IllegalClassFormatException {

    // skip bootstrap classes
    if ( loader == null ) {
      return classfileBuffer;
    }
    // skip java runtime classes
    for ( String ignored : ignore ) {
      if ( className.startsWith( ignored ) ) {
        return classfileBuffer;
      }
    }

    byte[] byteCode = classfileBuffer;

    com.kishore.trace.logging.loggers.TraceAgentLogger
        .getTraceAgentLoggerInstance().logFinest( "Instrumenting......" );
    try {
      final ClassPool classPool = ClassPool.getDefault();
      final CtClass ctClass = classPool.makeClass( new ByteArrayInputStream(
          classfileBuffer ) );
      final CtMethod[] methods = ctClass.getDeclaredMethods();
      for ( CtMethod method : methods ) {
        final String currentExecution = "class [" + ctClass.getName()
            + "], method [" + method.getName() + "]";
        final String logCommand = "com.kishore.trace.logging.loggers."
            + "TraceAgentLogger.getTraceAgentLoggerInstance()."
            + "logInfo(\"Execution in " + currentExecution + "\");";
        method.insertBefore( logCommand );
        com.kishore.trace.logging.loggers.TraceAgentLogger
            .getTraceAgentLoggerInstance().logFinest(
                "Instrumentation of " + currentExecution + " completed" );
      }
      byteCode = ctClass.toBytecode();
      ctClass.detach();
      com.kishore.trace.logging.loggers.TraceAgentLogger
          .getTraceAgentLoggerInstance()
          .logFinest( "Instrumentation complete." );
    } catch ( Throwable ex ) {
      com.kishore.trace.logging.loggers.TraceAgentLogger
          .getTraceAgentLoggerInstance().logError(
              "error occurred while instrumenting , detailed message is ", ex );
    }

    return byteCode;
  }

}
