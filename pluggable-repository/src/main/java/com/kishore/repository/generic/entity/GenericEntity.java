/**
 * 
 */
package com.kishore.repository.generic.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class GenericEntity {
	
	
	/**
	 * 
	 */
	public GenericEntity() {
		// TODO Auto-generated constructor stub
	}
	
	private String collectionName = "CONFIG";
	
	private Map<String,Object> mapAttributes = new HashMap<String,Object>();
	
	public void putAttribute(String attributeName, Object value) {
		mapAttributes.put(attributeName, value);
	}

	public  Map<String, Object> getMapAttributes() {
		return Collections.unmodifiableMap(mapAttributes);
	}

}
