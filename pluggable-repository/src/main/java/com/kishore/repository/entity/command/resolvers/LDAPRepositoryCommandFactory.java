/**
 * 
 */
package com.kishore.repository.entity.command.resolvers;

import com.kishore.repository.entities.ConfigEntity;
import com.kishore.repository.entity.ldap.command.resolvers.ConfigEntityLDAPCommandResolver;

/**
 * The factory class to resolve the command for entity class for LDAP Service
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class LDAPRepositoryCommandFactory {

	public static RepositoryCommandResolver valueOf(String entityType) {
			
			if ((ConfigEntity.class.getName()).equals(entityType)) {
				return new ConfigEntityLDAPCommandResolver();
			}
			return null;
		}
	
}
