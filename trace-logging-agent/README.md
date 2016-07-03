Synopsis

This is small and simple project to demonstrate the use of java agent to attach the logger/tracing capability to any application externally without the need of any code change in application.
The application logs which method is being executed in external application.
This is useful when there is not enough logger in the external application. Its very much useful when you have bug multi-threaded application and difficult to know which method is in execution.


Install

run maven command as 
mvn install


Usage
java  -javaagent:trace-logging-agent-v1-jar-with-dependencies.jar  -jar your-application.jar


API reference
Java assist api has been used - http://jboss-javassist.github.io/javassist/

