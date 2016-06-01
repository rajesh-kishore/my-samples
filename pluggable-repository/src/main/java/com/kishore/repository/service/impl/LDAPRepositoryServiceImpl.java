/**
 * 
 */
package com.kishore.repository.service.impl;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.kishore.repository.entities.Entity;
import com.kishore.repository.entity.command.resolvers.LDAPRepositoryCommandResolver;
import com.kishore.repository.entity.command.resolvers.RepositoryCommandResolverFactory;
import com.kishore.repository.provider.impl.DBRepositoryProvider;
import com.kishore.repository.provider.impl.LDAPRepositoryProvider;
import com.kishore.repository.service.RepositoryService;
import com.kishore.repository.service.dao.LDAPRepositoryDAO;

/**
 * The LDAP repository service for {@link LDAPRepositoryProvider}
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class LDAPRepositoryServiceImpl implements RepositoryService {

	/**
	 * The LDAP repository DAO 
	 */
	private LDAPRepositoryDAO ldapRepositoryDAO = null;
	
	
	/**
	 * The spring context to avail spring services
	 */
	private ApplicationContext appContext = null;
	
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
	public void create(Entity entity) {
		LDAPRepositoryCommandResolver repositoryCommandResolver = (LDAPRepositoryCommandResolver) RepositoryCommandResolverFactory.valueOf(repositroyServiceName(),entity.getClass().getName());
		Map<String,Object> mapAttributes = repositoryCommandResolver.resolveCreateCommand(entity);
		getLdapRepositoryDAO().insert(mapAttributes);
		
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#update(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void update(Entity entity) {
		LDAPRepositoryCommandResolver repositoryCommandResolver = (LDAPRepositoryCommandResolver) RepositoryCommandResolverFactory.valueOf(repositroyServiceName(),entity.getClass().getName());
		
		getLdapRepositoryDAO().update(repositoryCommandResolver.resolveUpdateCommand(entity));
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.RepositoryService#delete(com.kishore.repository.generic.entity.GenericEntity)
	 */
	public void delete(Entity entity) {
		LDAPRepositoryCommandResolver repositoryCommandResolver = (LDAPRepositoryCommandResolver) RepositoryCommandResolverFactory.valueOf(repositroyServiceName(),entity.getClass().getName());
		getLdapRepositoryDAO().delete(repositoryCommandResolver.resolveDeleteCommand(entity));
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


	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		appContext = arg0;		
	}


	public void setLdapRepositoryDAO(LDAPRepositoryDAO ldapRepositoryDAO) {
		this.ldapRepositoryDAO = ldapRepositoryDAO;
	}

}
