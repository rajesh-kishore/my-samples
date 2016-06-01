/**
 * 
 */
package com.kishore.repository.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.kishore.repository.entities.Entity;
import com.kishore.repository.entity.command.resolvers.DBRepositoryCommandResolver;
import com.kishore.repository.entity.command.resolvers.RepositoryCommandResolverFactory;
import com.kishore.repository.provider.impl.DBRepositoryProvider;
import com.kishore.repository.service.RepositoryService;
import com.kishore.repository.service.dao.DBRepositoryDAO;

/**
 * The DB repository service for {@link DBRepositoryProvider}
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class DBRepositoryServiceImpl implements RepositoryService {

	/**
	 * The DB repository DAO 
	 */
	private DBRepositoryDAO dbRepositoryDAO = null;
	
	/**
	 * The spring context to avail spring services
	 */
	private ApplicationContext appContext = null;
	
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
	public void create(Entity entity) {
		DBRepositoryCommandResolver repositoryCommandResolver = (DBRepositoryCommandResolver) RepositoryCommandResolverFactory.valueOf(repositroyServiceName(),entity.getClass().getName());
		String insertStatement = repositoryCommandResolver.resolveCreateCommand(entity);
		this.getDbRepositoryDAO().insert(insertStatement);
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#update(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void update(Entity entity) {
		DBRepositoryCommandResolver repositoryCommandResolver = (DBRepositoryCommandResolver) RepositoryCommandResolverFactory.valueOf(repositroyServiceName(),entity.getClass().getName());
		String updateStatement = repositoryCommandResolver.resolveUpdateCommand(entity);
		this.getDbRepositoryDAO().insert(updateStatement);
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#delete(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void delete(Entity entity) {
		DBRepositoryCommandResolver repositoryCommandResolver = (DBRepositoryCommandResolver) RepositoryCommandResolverFactory.valueOf(repositroyServiceName(),entity.getClass().getName());
		String deleteStatement = (String) repositoryCommandResolver.resolveDeleteCommand(entity);
		this.getDbRepositoryDAO().insert(deleteStatement);
	}
	
	
	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#repositroyServiceName()
	 */
	public String repositroyServiceName() {
		return "DB";
	}


	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		appContext = arg0;	
	}

	public DBRepositoryDAO getDbRepositoryDAO() {
		return dbRepositoryDAO;
	}

	public void setDbRepositoryDAO(DBRepositoryDAO dbRepositoryDAO) {
		this.dbRepositoryDAO = dbRepositoryDAO;
	}

}
