/**
 * 
 */
package com.kishore.repository.provider;

import com.kishore.repository.service.RepositoryService;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public interface RepositoryProvider {
	
	
	/**
	 * Creates the RepositoryService
	 * @return The {@link RepositoryService}
	 */
	public RepositoryService createRepositoryService();

}
