/**
 * 
 */
package com.kishore.repository.entity.command.resolvers;

import com.kishore.repository.entity.db.command.resolvers.GenericEntityDBCommandResolver;
import com.kishore.repository.entity.ldap.command.resolvers.GenericEntityLDAPCommandResolver;
import com.kishore.repository.generic.entity.GenericEntity;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public final class RepositoryCommandResolverFactory {
	
	public static RepositoryCommandResolver valueOf(String repositoryServiceType, String entityType) {
		
		if (("DB:"+GenericEntity.class.getName()).equals(repositoryServiceType+":"+entityType)) {
			return new GenericEntityDBCommandResolver();
		}
		
		if (("LDAP:"+GenericEntity.class.getName()).equals(repositoryServiceType+":"+entityType)) {
			return new GenericEntityLDAPCommandResolver();
		}
		
		return null;
	}

}
