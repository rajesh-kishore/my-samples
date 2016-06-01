/**
 * 
 */
package com.kishore.repository.entities;

import java.util.Map;

/**
 * One of the concrete implementation of {@link Entity} which considers attributes as property
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class ConfigEntity extends Entity {

	/* (non-Javadoc)
	 * @see com.kishore.repository.entities.Entity#putAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void putAttribute(String attributeName, Object value) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.entities.Entity#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * The constructor to set the properties
	 */
	public ConfigEntity(String attribute1,String attribute2) {
		this.setSomeAttribute1(attribute1);
		this.setSomeAttribute2(attribute2);
	}
	
	/**
	 * one of the attribute
	 */
	private String someAttribute1;
	
	/**
	 * one of the attribute
	 */
	private String someAttribute2;

	public String getSomeAttribute1() {
		return someAttribute1;
	}

	private void setSomeAttribute1(String someAttribute1) {
		this.someAttribute1 = someAttribute1;
	}

	public String getSomeAttribute2() {
		return someAttribute2;
	}

	private void setSomeAttribute2(String someAttribute2) {
		this.someAttribute2 = someAttribute2;
	}

}
