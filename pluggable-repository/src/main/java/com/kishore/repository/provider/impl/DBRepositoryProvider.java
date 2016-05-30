/**
 * 
 */
package com.kishore.repository.provider.impl;

import com.kishore.repository.provider.RepositoryProvider;
import com.kishore.repository.service.RepositoryService;
import com.kishore.repository.service.impl.DBRepositoryServiceImpl;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class DBRepositoryProvider implements RepositoryProvider {

	
	/**
	 * The private constructor to only ensure only single instance can be created
	 */
	private  DBRepositoryProvider() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The private inner class acting as a helper to create the singleton 
	 * @since Release1
	 */
	private static class DBRepositoryProviderSingletonCreator {
		/**
		 *  The static private repositoryProvider
		 */
		private static RepositoryProvider repositoryProvider = new DBRepositoryProvider();
	}
	
	public static RepositoryProvider getInstance() {
		return DBRepositoryProviderSingletonCreator.repositoryProvider;
	}
	
	
	/* (non-Javadoc)
	 * @see com.kishore.repository.provider.RepositoryProvider#createRepositoryService()
	 */
	public RepositoryService createRepositoryService() {
		// TODO Auto-generated method stub
		return DBRepositoryServiceImpl.getInstance();
	}

}
