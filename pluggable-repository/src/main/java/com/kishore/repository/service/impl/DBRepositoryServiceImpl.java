/**
 * 
 */
package com.kishore.repository.service.impl;

import com.kishore.repository.entity.command.resolvers.RepositoryCommandResolver;
import com.kishore.repository.entity.command.resolvers.RepositoryCommandResolverFactory;
import com.kishore.repository.generic.entity.GenericEntity;
import com.kishore.repository.service.RepositoryService;
import com.kishore.repository.service.dao.DBRepositoryDAO;
import com.kishore.repository.service.dao.impl.DBRepositoryDAOImpl;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class DBRepositoryServiceImpl implements RepositoryService {

	private DBRepositoryDAO dBRepositoryDAO = new DBRepositoryDAOImpl();
	
	/**
	 * The private constructor created to allow only instance of this class
	 */
	private DBRepositoryServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The private inner class acting as a helper to create the singleton 
	 * @since Release1
	 */
	private static class DBRepositoryServiceSingletonCreator {
		/**
		 *  The static private repositoryService
		 */
		private static RepositoryService repositoryService = new DBRepositoryServiceImpl();
	}
	
	
	/**
	 * The factory method to create {@link DBRepositoryServiceImpl}
	 * @return
	 */
	public static RepositoryService getInstance() {
		return DBRepositoryServiceSingletonCreator.repositoryService;
	}
	
	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#create(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void create(GenericEntity genericEntity) {
		RepositoryCommandResolver repositoryCommandResolver = RepositoryCommandResolverFactory.valueOf(repositroyServiceName(),genericEntity.getClass().getName());
		String insertStatement = repositoryCommandResolver.resolveCreateStatementCommand(genericEntity);
		this.getDBRepositoryDAO().insert(insertStatement);
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#update(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void update(GenericEntity genericEntity) {
		RepositoryCommandResolver repositoryCommandResolver = RepositoryCommandResolverFactory.valueOf(repositroyServiceName(),genericEntity.getClass().getName());
		String updateStatement = repositoryCommandResolver.resolveUpdateStatementCommand(genericEntity);
		this.getDBRepositoryDAO().insert(updateStatement);
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#delete(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void delete(GenericEntity genericEntity) {
		RepositoryCommandResolver repositoryCommandResolver = RepositoryCommandResolverFactory.valueOf(repositroyServiceName(),genericEntity.getClass().getName());
		String deleteStatement = repositoryCommandResolver.resolveDeleteStatementCommand(genericEntity);
		this.getDBRepositoryDAO().insert(deleteStatement);
	}
	
	
	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#repositroyServiceName()
	 */
	public String repositroyServiceName() {
		return "DB";
	}

	private DBRepositoryDAO getDBRepositoryDAO() {
		return dBRepositoryDAO;
	}

}
