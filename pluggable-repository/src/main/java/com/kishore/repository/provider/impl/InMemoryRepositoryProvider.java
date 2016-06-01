/**
 * 
 */
package com.kishore.repository.provider.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.kishore.repository.provider.RepositoryProvider;
import com.kishore.repository.service.RepositoryService;
import com.kishore.repository.service.impl.InMemoryRepositoryServiceImpl;

/**
 * The OOTB InMemory RepositoryProvider which provides InMemory repository service
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class InMemoryRepositoryProvider implements RepositoryProvider {

	
	/**
	 * The spring specific services
	 */
	private ApplicationContext appContext;
	
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
		return appContext != null ? (InMemoryRepositoryServiceImpl) appContext.getBean("InMemoryRepositoryServiceImpl") : InMemoryRepositoryServiceImpl.getInstance();
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
