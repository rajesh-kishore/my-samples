/**
 * 
 */
package com.kishore.repository.service.impl;

import com.kishore.repository.entity.command.resolvers.RepositoryCommandResolver;
import com.kishore.repository.entity.command.resolvers.RepositoryCommandResolverFactory;
import com.kishore.repository.generic.entity.GenericEntity;
import com.kishore.repository.service.RepositoryService;
import com.kishore.repository.service.dao.LDAPRepositoryDAO;
import com.kishore.repository.service.dao.impl.LDAPRepositoryDAOImpl;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class LDAPRepositoryServiceImpl implements RepositoryService {

	LDAPRepositoryDAO ldapRepositoryDAO = new LDAPRepositoryDAOImpl();
	
	/**
	 * The private constructor created to allow only instance of this class
	 */
	private LDAPRepositoryServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The private inner class acting as a helper to create the singleton 
	 * @since Release1
	 */
	private static class LDAPRepositoryServiceSingletonCreator {
		/**
		 *  The static private repositoryService
		 */
		private static RepositoryService repositoryService = new LDAPRepositoryServiceImpl();
	}
	
	
	/**
	 * The factory method to create {@link LDAPRepositoryServiceImpl}
	 * @return
	 */
	public static RepositoryService getInstance() {
		return LDAPRepositoryServiceSingletonCreator.repositoryService;
	}
	
	
	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#create(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void create(GenericEntity genericEntity) {
		RepositoryCommandResolver repositoryCommandResolver = RepositoryCommandResolverFactory.valueOf(repositroyServiceName(),genericEntity.getClass().getName());
		repositoryCommandResolver.resolveCreateStatementCommand(genericEntity);
		getLdapRepositoryDAO().insert();
		
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#update(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void update(GenericEntity genericEntity) {
		RepositoryCommandResolver repositoryCommandResolver = RepositoryCommandResolverFactory.valueOf(repositroyServiceName(),genericEntity.getClass().getName());
		repositoryCommandResolver.resolveCreateStatementCommand(genericEntity);
		getLdapRepositoryDAO().update();
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#delete(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void delete(GenericEntity genericEntity) {
		RepositoryCommandResolver repositoryCommandResolver = RepositoryCommandResolverFactory.valueOf(repositroyServiceName(),genericEntity.getClass().getName());
		repositoryCommandResolver.resolveCreateStatementCommand(genericEntity);
		getLdapRepositoryDAO().delete();
	}


	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#repositroyServiceName()
	 */
	public String repositroyServiceName() {
		// TODO Auto-generated method stub
		return "LDAP";
	}


	private final LDAPRepositoryDAO getLdapRepositoryDAO() {
		return ldapRepositoryDAO;
	}

}
