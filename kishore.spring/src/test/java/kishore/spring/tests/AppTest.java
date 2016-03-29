/**
 * 
 */
package kishore.spring.tests;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kishore.services.HelloWorldService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    
    public void testConfig() {
    	ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
 
		HelloWorldService service = (HelloWorldService) context
				.getBean("helloWorldService");
		assertEquals("It is not expected", "Hello Program Creek Readers", service.sayHello());
		String message = service.sayHello();
    }
}
