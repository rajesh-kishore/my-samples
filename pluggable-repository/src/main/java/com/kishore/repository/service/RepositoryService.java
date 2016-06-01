/**
 * 
 */
package com.kishore.repository.service;

import org.springframework.context.ApplicationContextAware;

import com.kishore.repository.entities.Entity;

/**
 * The different repository services needs to be implemented by different provider implementation
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public interface RepositoryService extends ApplicationContextAware {

	/**
	 * The api to create the entity in registered repository provider's service
	 * @param entity The entity to be created
	 */
	public void create(Entity entity);
	
	/**
	 * The api to update the entity in registered repository provider's service
	 * @param entity The entity to be updated
	 */
	public void update(Entity entity);
	
	/**
	 * The api to delete the entity in registered repository provider's service
	 * @param entity The entity to be deleted
	 */
	public void delete(Entity entity);
	
	/**
	 * The api to return corresponding repository service name in context
	 * @return The corresponding repository service in context
	 */
	public String repositroyServiceName();
	
}
