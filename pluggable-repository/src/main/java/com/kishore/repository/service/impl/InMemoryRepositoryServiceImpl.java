/**
 * 
 */
package com.kishore.repository.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.kishore.repository.entities.Entity;
import com.kishore.repository.service.RepositoryService;

/**
 * The InMemory repository service for {@link InMemoryRepositoryServiceImpl}
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class InMemoryRepositoryServiceImpl implements RepositoryService {

	
	/**
	 * The spring context to avail spring services
	 */
	private ApplicationContext appContext;
	
	/**
	 * The private constructor created to allow only instance of this class
	 */
	private InMemoryRepositoryServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The private inner class acting as a helper to create the singleton 
	 * @since Release1
	 */
	private static class InMemoryRepositoryServiceSingletonCreator {
		/**
		 *  The static private repositoryService
		 */
		private static RepositoryService repositoryService = new InMemoryRepositoryServiceImpl();
	}
	
	
	/**
	 * The factory method to create {@link InMemoryRepositoryServiceImpl}
	 * @return
	 */
	public static RepositoryService getInstance() {
		return InMemoryRepositoryServiceSingletonCreator.repositoryService;
	}
	
	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#create(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void create(Entity entity) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#update(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void update(Entity entity) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#delete(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void delete(Entity entity) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#repositroyServiceName()
	 */
	public String repositroyServiceName() {
		// TODO Auto-generated method stub
		return "InMemory";
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			 {

		appContext = arg0;
	}

}
