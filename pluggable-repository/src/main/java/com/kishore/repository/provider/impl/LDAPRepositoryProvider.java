/**
 * 
 */
package com.kishore.repository.provider.impl;

import com.kishore.repository.provider.RepositoryProvider;
import com.kishore.repository.service.RepositoryService;
import com.kishore.repository.service.impl.LDAPRepositoryServiceImpl;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class LDAPRepositoryProvider implements RepositoryProvider {

	
	/**
	 * The private constructor to only ensure only single instance can be created
	 */
	private  LDAPRepositoryProvider() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The private inner class acting as a helper to create the singleton 
	 * @since Release1
	 */
	private static class LDAPRepositoryProviderSingletonCreator {
		/**
		 *  The static private repositoryProvider
		 */
		private static RepositoryProvider repositoryProvider = new LDAPRepositoryProvider();
	}
	
	public static RepositoryProvider getInstance() {
		return LDAPRepositoryProviderSingletonCreator.repositoryProvider;
	}
	
	/* (non-Javadoc)
	 * @see com.kishore.repository.provider.RepositoryProvider#createRepositoryService()
	 */
	public RepositoryService createRepositoryService() {
		// TODO Auto-generated method stub
		return LDAPRepositoryServiceImpl.getInstance();
	}

}
