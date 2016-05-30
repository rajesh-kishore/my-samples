/**
 * 
 */
package com.kishore.repository.service.impl;

import com.kishore.repository.generic.entity.GenericEntity;
import com.kishore.repository.service.RepositoryService;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class InMemoryRepositoryServiceImpl implements RepositoryService {

	
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
	 * The feactory method to create {@link InMemoryRepositoryServiceImpl}
	 * @return
	 */
	public static RepositoryService getInstance() {
		return InMemoryRepositoryServiceSingletonCreator.repositoryService;
	}
	
	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#create(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void create(GenericEntity genericEntity) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#update(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void update(GenericEntity genericEntity) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#delete(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void delete(GenericEntity genericEntity) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#repositroyServiceName()
	 */
	public String repositroyServiceName() {
		// TODO Auto-generated method stub
		return "InMemory";
	}

}
