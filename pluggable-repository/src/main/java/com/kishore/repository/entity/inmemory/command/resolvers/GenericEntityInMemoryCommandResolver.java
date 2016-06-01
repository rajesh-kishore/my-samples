/**
 * 
 */
package com.kishore.repository.entity.inmemory.command.resolvers;

import com.kishore.repository.entity.command.resolvers.DBRepositoryCommandResolver;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class GenericEntityInMemoryCommandResolver implements
		DBRepositoryCommandResolver {

	/* (non-Javadoc)
	 * @see com.kishore.repository.entity.command.resolvers.RepositoryCommandResolver#resolveCreateStatementCommand(java.lang.Object)
	 */
	public String resolveCreateCommand(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.entity.command.resolvers.RepositoryCommandResolver#resolveUpdateStatementCommand(java.lang.Object)
	 */
	public String resolveUpdateCommand(Object entity) {
		// TODO Auto-generated method stub
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
