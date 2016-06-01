/**
 * 
 */
package com.kishore.repository.entities;

/**
 * The abstract class Entity is to define the attributes
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public abstract class Entity {

	
	/**
	 * Pushes the attribute to corresponding underlying concrete class
	 * @param attributeName
	 * @param value
	 */
	public abstract void putAttribute(String attributeName, Object value);
	
	/**
	 * Extracts the atttribute value from underlying concrete class
	 * @param attributeName
	 * @return
	 */
	public abstract Object getAttribute(String attributeName);
	
}
