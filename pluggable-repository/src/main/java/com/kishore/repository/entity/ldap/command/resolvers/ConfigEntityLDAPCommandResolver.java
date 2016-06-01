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

import com.kishore.repository.entities.ConfigEntity;
import com.kishore.repository.entities.Entity;
import com.kishore.repository.entity.command.resolvers.LDAPRepositoryCommandResolver;

/**
 * The ConfigEntity command resolver class for LDAP service
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class ConfigEntityLDAPCommandResolver implements LDAPRepositoryCommandResolver {

	/* (non-Javadoc)
	 * @see com.kishore.repository.entity.atribute.resolvers.AttributesResolvable#createStatementAttributeResolver(java.lang.Object)
	 */
	public Map<String,Object> resolveCreateCommand(Entity entity) {
		Map<String,Object> mapAttributes = new HashMap<String, Object>();
		ConfigEntity configEntity = (ConfigEntity) entity;
		DistinguishedName dn = new DistinguishedName("dc=config");
		String cn = System.currentTimeMillis()+"";
		dn.add("cn",cn);
		Attributes entityAttributes = new BasicAttributes();
	    BasicAttribute objectClassBasicAttribute = new BasicAttribute("objectclass");
	    objectClassBasicAttribute.add("customConfigObjectClass");
	    objectClassBasicAttribute.add("top");
	    entityAttributes.put(objectClassBasicAttribute);
	    entityAttributes.put("cn", cn);
	    entityAttributes.put("someAttribute1", configEntity.getSomeAttribute1());
	    entityAttributes.put("someAttribute2", configEntity.getSomeAttribute2());

	    mapAttributes.put("dn", dn);
	    mapAttributes.put("attributes", entityAttributes);
	    return mapAttributes;
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.entity.atribute.resolvers.AttributesResolvable#updateStatementAttributeResolver(java.lang.Object)
	 */
	public Map<String,Object> resolveUpdateCommand(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.kishore.repository.entity.command.resolvers.RepositoryCommandResolver#resolveDeleteStatementCommand(java.lang.Object)
	 */
	public Map<String,Object> resolveDeleteCommand(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
