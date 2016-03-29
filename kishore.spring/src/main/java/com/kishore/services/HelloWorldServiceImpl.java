/**
 * 
 */
package com.kishore.services;

/**
 * @author rakishor
 *
 */
final public class HelloWorldServiceImpl implements HelloWorldService {
	
	
	private String name;

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}
	
	
	public String sayHello() {
		return "Hello "+ getName();
	}

}
