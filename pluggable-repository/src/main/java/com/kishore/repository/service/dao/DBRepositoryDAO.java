/**
 * 
 */
package com.kishore.repository.service.dao;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public interface DBRepositoryDAO {
	
	public void insert(String insertStatement);
	
	public void delete(String deleteStatement);
	
	public void update(String updateStatement);

}
