/**
 * 
 */
package com.kishore.repository.service.dao;

import com.kishore.repository.service.impl.DBRepositoryServiceImpl;


/**
 * The DB DAO to be used by {@link DBRepositoryServiceImpl}
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public interface DBRepositoryDAO {
	
	/**
	 * The insert api
	 * @param insertStatement The SQL statement
	 */
	public void insert(String insertStatement);
	
	/**
	 * The delete api
	 * @param deleteStatement The SQL statement
	 */
	public void delete(String deleteStatement);
	
	/**
	 * The update api
	 * @param updateStatement The SQL statement
	 */
	public void update(String updateStatement);

}
