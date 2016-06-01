/**
 * 
 */
package com.kishore.repository.service.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.kishore.repository.service.dao.DBRepositoryDAO;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class DBRepositoryDAOImpl extends JdbcDaoSupport implements DBRepositoryDAO {

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.dao.DBRepositoryDAO#insert()
	 */
	public void insert(String insertStatement) {
		getJdbcTemplate().execute(insertStatement);

	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.dao.DBRepositoryDAO#delete()
	 */
	public void delete(String deleteStatement) {
		getJdbcTemplate().execute(deleteStatement);
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.dao.DBRepositoryDAO#update()
	 */
	public void update(String updateStatement) {
		getJdbcTemplate().execute(updateStatement);
	}

}
