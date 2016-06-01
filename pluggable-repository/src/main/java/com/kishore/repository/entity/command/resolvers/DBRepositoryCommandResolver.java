/**
 * 
 */
package com.kishore.repository.entity.command.resolvers;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public interface DBRepositoryCommandResolver extends RepositoryCommandResolver {
	
	public String resolveCreateCommand(Object entity);
	
	public String resolveUpdateCommand(Object entity);
	
	public String resolveDeleteCommand(Object entity);

}
