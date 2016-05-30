/**
 * 
 */
package com.kishore.repository.provider.impl;

import com.kishore.repository.provider.RepositoryProvider;
import com.kishore.repository.service.RepositoryService;
import com.kishore.repository.service.impl.InMemoryRepositoryServiceImpl;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class InMemoryRepositoryProvider implements RepositoryProvider {

	
	/**
	 * The private constructor to only ensure only single instance can be created
	 */
	private  InMemoryRepositoryProvider() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The private inner class acting as a helper to create the singleton 
	 * @since Release1
	 */
	private static class InMemoryRepositoryProviderSingletonCreator {
		/**
		 *  The static private repositoryProvider
		 */
		private static RepositoryProvider repositoryProvider = new InMemoryRepositoryProvider();
	}
	
	public static RepositoryProvider getInstance() {
		return InMemoryRepositoryProviderSingletonCreator.repositoryProvider;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.kishore.repository.provider.RepositoryProvider#createRepositoryService()
	 */
	public RepositoryService createRepositoryService() {
		// TODO Auto-generated method stub
		return InMemoryRepositoryServiceImpl.getInstance();
	}

}
