/**
 * 
 */
package com.kishore.repository.entity.db.command.resolvers;

import java.util.Map;

import com.kishore.repository.entity.command.resolvers.RepositoryCommandResolver;
import com.kishore.repository.generic.entity.GenericEntity;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class GenericEntityDBCommandResolver implements RepositoryCommandResolver {

	
	public String resolveCreateCommand(Object entity) {
		GenericEntity genericEntity = (GenericEntity) entity;
		Map<String,Object> attributes = genericEntity.getMapAttributes();
		StringBuffer sbInsertStatement = new StringBuffer();
		sbInsertStatement.append(" insert into config values ("+attributes.get("someAttribute1")+","+attributes.get("someAttribute2")+")");
		return sbInsertStatement.toString(); 
	}
	
	public String resolveUpdateCommand(Object entity) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.entity.command.resolvers.RepositoryCommandResolver#resolveDeleteStatementCommand(java.lang.Object)
	 */
	public String resolveDeleteCommand(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
 