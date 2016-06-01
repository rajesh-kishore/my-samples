/**
 * 
 */
package com.kishore.repository.entity.ldap.command.resolvers;

import java.util.HashMap;
import java.util.Map;

import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

import org.springframework.ldap.core.DistinguishedName;

import com.kishore.repository.entity.command.resolvers.RepositoryCommandResolver;
import com.kishore.repository.generic.entity.GenericEntity;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class GenericEntityLDAPCommandResolver implements RepositoryCommandResolver {

	/* (non-Javadoc)
	 * @see com.kishore.repository.entity.atribute.resolvers.AttributesResolvable#createStatementAttributeResolver(java.lang.Object)
	 */
	public Map<String,Object> resolveCreateCommand(Object entity) {
		Map<String,Object> mapAttributes = new HashMap<String, Object>();
		GenericEntity genericEntity = (GenericEntity) entity;
		DistinguishedName dn = new DistinguishedName("dc=config");
		String cn = System.currentTimeMillis()+"";
		dn.add("cn",cn);
		Attributes entityAttributes = new BasicAttributes();
	    BasicAttribute objectClassBasicAttribute = new BasicAttribute("objectclass");
	    objectClassBasicAttribute.add("customConfigObjectClass");
	    objectClassBasicAttribute.add("top");
	    entityAttributes.put(objectClassBasicAttribute);
	    entityAttributes.put("cn", cn);
	    entityAttributes.put("someAttribute1", genericEntity.getMapAttributes().get("someAttribute1"));
	    entityAttributes.put("someAttribute2", genericEntity.getMapAttributes().get("someAttribute2"));

	    mapAttributes.put("dn", dn);
	    mapAttributes.put("attributes", entityAttributes);
	    return mapAttributes;
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.entity.atribute.resolvers.AttributesResolvable#updateStatementAttributeResolver(java.lang.Object)
	 */
	public Map<String,Object> resolveUpdateCommand(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.entity.command.resolvers.RepositoryCommandResolver#resolveDeleteStatementCommand(java.lang.Object)
	 */
	public Map<String,Object> resolveDeleteCommand(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
