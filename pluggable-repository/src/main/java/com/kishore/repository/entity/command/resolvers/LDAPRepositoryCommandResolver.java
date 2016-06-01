/**
 * 
 */
package com.kishore.repository.entity.command.resolvers;

import java.util.Map;

import com.kishore.repository.entities.Entity;

/**
 * The command resolver for LDAP based repository service
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public interface LDAPRepositoryCommandResolver extends RepositoryCommandResolver {

	/**
	 * @param entity one of the entity class object
	 * @return LDAP specific attributes
	 */
	public Map<String,Object> resolveCreateCommand(Entity entity);
	
	/**
	 * @param entity one of the entity class object
	 * @return LDAP specific attributes
	 */
	public Map<String,Object> resolveUpdateCommand(Entity entity);
	
	/**
	 * @param entity one of the entity class object
	 * @return LDAP specific attributes
	 */
	public Map<String,Object> resolveDeleteCommand(Entity entity);
}
