/**
 * 
 */
package com.kishore.repository.service.dao.impl;

import java.util.Map;

import javax.naming.directory.Attributes;

import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;

import com.kishore.repository.service.dao.LDAPRepositoryDAO;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class LDAPRepositoryDAOImpl implements LDAPRepositoryDAO {
	
	LdapTemplate ldapTemplate = null;

	/* (non-Javadoc)
	 * @see com.kishore.repository.service.dao.LDAPRepositoryDAO#delete()
	 */
	public void delete(Map<String,Object> mapAttributes) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see com.kishore.repository.service.dao.LDAPRepositoryDAO#insert()
	 */
	public void insert(Map<String,Object> mapAttributes) {
		// TODO Auto-generated method stub
		ldapTemplate.bind((DistinguishedName)mapAttributes.get("dn"),null,(Attributes)mapAttributes.get("attributes"));
	}
	
	/* (non-Javadoc)
	 * @see com.kishore.repository.service.dao.LDAPRepositoryDAO#update()
	 */
	public void update(Map<String,Object> mapAttributes) {
		// TODO Auto-generated method stub
		
	}

	private final LdapTemplate getLdapTemplate() {
		return ldapTemplate;
	}

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}
}
