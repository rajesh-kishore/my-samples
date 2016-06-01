/**
 * 
 */
package com.kishore.repository.service;

import org.springframework.context.ApplicationContextAware;

import com.kishore.repository.generic.entity.GenericEntity;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public interface RepositoryService extends ApplicationContextAware {

	public void create(GenericEntity genericEntity);
	
	public void update(GenericEntity genericEntity);
	
	public void delete(GenericEntity genericEntity);
	
	public String repositroyServiceName();
	
}
