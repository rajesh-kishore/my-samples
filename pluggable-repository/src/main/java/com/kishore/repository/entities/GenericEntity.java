/**
 * 
 */
package com.kishore.repository.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * One of the concrete implementation of {@link Entity} which considers attributes as {@link Map}
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class GenericEntity extends Entity {
	
	
	/**
	 * The public constructor 
	 */
	public GenericEntity() {

	}
	
	/**
	 * Attributes are stored in Map
	 */
	private Map<String,Object> mapAttributes = new HashMap<String,Object>();
	
	
	/* (non-Javadoc)
	 * @see com.kishore.repository.entities.Entity#putAttribute(java.lang.String, java.lang.Object)
	 */
	public void putAttribute(String attributeName, Object value) {
		mapAttributes.put(attributeName, value);
	}

	/**
	 * This returns the map attribute as unmodifiable
	 * @return
	 */
	public  Map<String, Object> getMapAttributes() {
		return Collections.unmodifiableMap(mapAttributes);
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.entities.Entity#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(String attributeName) {
		return mapAttributes.get(attributeName);
	}

}
