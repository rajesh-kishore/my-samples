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
	
	public String resolveCreateStatementCommand(Object entity);
	
	public String resolveUpdateStatementCommand(Object entity);
	
	public String resolveDeleteStatementCommand(Object entity);

}
