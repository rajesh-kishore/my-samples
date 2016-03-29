/**
 * 
 */
package com.kishore.clients;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kishore.services.HelloWorldService;

/**
 * @author rakishor
 *
 */
public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
 
		HelloWorldService service = (HelloWorldService) context
				.getBean("helloWorldService");
		String message = service.sayHello();
		System.out.println(message);
	}

}
