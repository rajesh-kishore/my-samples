/**
 * 
 */
package com.kishore.repository.service.dao;

import java.util.Map;

import com.kishore.repository.service.impl.LDAPRepositoryServiceImpl;

/**
 * The LDAP DAO to be used by {@link LDAPRepositoryServiceImpl}
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public interface LDAPRepositoryDAO {

	/**
	 * The insert api
	 * @param mapAttributes The ldap specific attributes
	 */
	public void insert(Map<String,Object> mapAttributes);
	
	/**
	 * The delete api
	 * @param mapAttributes The ldap specific attributes
	 */
	public void delete(Map<String,Object> mapAttributes);
	
	/**
	 * The update api
	 * @param mapAttributes The ldap specific attributes
	 */
	public void update(Map<String,Object> mapAttributes);
	
}
