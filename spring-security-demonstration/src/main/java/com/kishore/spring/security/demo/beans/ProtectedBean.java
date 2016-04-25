/**
 * 
 */
package com.kishore.spring.security.demo.beans;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * The Spring protected class which can be accessed only by authorized user 
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class ProtectedBean {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProtectedBean";
	}
	
	/**
	 * The method can be accessed only by authorized user who has 'ROLE_USER' role
	 * @return onlyRoleUserCanAccess as string for users who are permitted to access this method
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	public String onlyRoleUserCanAccess() {
		return "onlyRoleUserCanAccess";
	}
	
	/**
	 * The method can be accessed by any user
	 * @return The string anyOneCanAccess is returned for any user
	 */
	public String anyOneCanAccess() {
		return "anyOneCanAccess";
	}
}
