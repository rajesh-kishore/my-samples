/**
 * 
 */
package com.kishore.spring.security.demo.authentication.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.kishore.spring.security.demo.utils.loggers.SecurityDemoLogger;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class CustomAuthenticationManager implements AuthenticationManager {

	
	/**
	 * The authorities are like role defined
	 */
	private static final List<GrantedAuthority> AUTHORITIES_MASTER = new ArrayList<GrantedAuthority>();

	static {
		AUTHORITIES_MASTER.add(new SimpleGrantedAuthority("ROLE_USER"));
	 }
	  
	 /**
	 * The guest role defined 
	 */
	private static final List<GrantedAuthority> AUTHORITIES_GUEST = new ArrayList<GrantedAuthority>();
	
	static {
		  AUTHORITIES_GUEST.add(new SimpleGrantedAuthority("ROLE_GUEST"));
	}
	 
	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationManager#authenticate(org.springframework.security.core.Authentication)
	 */
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		
		  List<GrantedAuthority> calculatedAuthorities = AUTHORITIES_MASTER;
		  
		  if (((UsernamePasswordAuthenticationToken)auth).getName().equals("guestuser")) {
			  calculatedAuthorities = AUTHORITIES_GUEST;
		   }
		   
		  SecurityDemoLogger.SECURITYDEMOLOGGER.info(" The logged in user has got following roles "+AUTHORITIES_GUEST.get(0).getAuthority());
		   
		   if (auth.getName().equals(auth.getCredentials())) {
			   SecurityDemoLogger.SECURITYDEMOLOGGER.info(" The logged in user's authentication succeeded");
		       
			   return new UsernamePasswordAuthenticationToken(auth.getName(),auth.getCredentials(), calculatedAuthorities);
		   }
		   
		   SecurityDemoLogger.SECURITYDEMOLOGGER.info(" The logged in user's authentication failed , throwing exception");
		   throw new BadCredentialsException("Bad Credentials");
	}

}
