/**
 * 
 */
package com.kishore.repository.entity.command.resolvers;


/**
 * The abstract factory class which determines one of the corresponding Factory based on repository service and returns the correct command resolver
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public final class RepositoryCommandResolverFactory {
	
	/**
	 * 
	 * @param repositoryServiceType - Could be LDAP, DB, InMemory
	 * @param entityType - full class name of the entity used
	 * @return
	 */
	public static RepositoryCommandResolver valueOf(String repositoryServiceType, String entityType) {
		
		if ("DB".equals(repositoryServiceType)) {
			return DBRepositoryCommandResolverFactory.valueOf(entityType);
		}
		
		if ("LDAP".equals(repositoryServiceType)) {
			return LDAPRepositoryCommandFactory.valueOf(entityType);
		}
		
		return null;
	}

}
