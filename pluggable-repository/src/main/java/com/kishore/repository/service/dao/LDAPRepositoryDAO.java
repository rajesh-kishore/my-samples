/**
 * 
 */
package com.kishore.repository.service.dao;

import java.util.Map;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public interface LDAPRepositoryDAO {

	public void insert(Map<String,Object> mapAttributes);
	
	public void delete(Map<String,Object> mapAttributes);
	
	public void update(Map<String,Object> mapAttributes);
	
}
