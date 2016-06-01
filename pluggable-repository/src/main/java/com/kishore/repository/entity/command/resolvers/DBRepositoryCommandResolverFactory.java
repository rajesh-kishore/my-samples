/**
 * 
 */
package com.kishore.repository.entity.command.resolvers;

import com.kishore.repository.entities.GenericEntity;
import com.kishore.repository.entity.db.command.resolvers.GenericEntityDBCommandResolver;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class DBRepositoryCommandResolverFactory {
	
	
	public static RepositoryCommandResolver valueOf(String entityType) {
		
		if ((GenericEntity.class.getName()).equals(entityType)) {
			return new GenericEntityDBCommandResolver();
		}
		return null;
	}
	
	
}
