/**
 * 
 */
package com.kishore.repository.provider.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.kishore.repository.provider.RepositoryProvider;
import com.kishore.repository.service.RepositoryService;
import com.kishore.repository.service.impl.LDAPRepositoryServiceImpl;

/**
 * The OOTB LDAP RepositoryProvider which provides LDAP repository service
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class LDAPRepositoryProvider implements RepositoryProvider {

	/**
	 * The spring specific services
	 */
	ApplicationContext appContext = null;
	
	/**
	 * The private constructor to only ensure only single instance can be created
	 */
	private  LDAPRepositoryProvider() {
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
		return appContext != null ? (LDAPRepositoryServiceImpl) appContext.getBean("LDAPRepositoryServiceImpl") : LDAPRepositoryServiceImpl.getInstance();
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		appContext = arg0;
	}

}
