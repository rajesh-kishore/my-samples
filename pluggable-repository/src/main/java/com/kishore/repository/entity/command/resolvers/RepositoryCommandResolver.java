/**
 * 
 */
package com.kishore.repository.entity.command.resolvers;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public interface RepositoryCommandResolver {
	
	public Object resolveCreateCommand(Object entity);
	
	public Object resolveUpdateCommand(Object entity);
	
	public Object resolveDeleteCommand(Object entity);

}
