/**
 * 
 */
package com.kishore.spring.security.demo.controllers;

import java.util.logging.Level;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kishore.spring.security.demo.authentication.manager.CustomAuthenticationManager;
import com.kishore.spring.security.demo.beans.ProtectedBean;
import com.kishore.spring.security.demo.config.SpringSecurityDemoAppConfig;
import com.kishore.spring.security.demo.utils.loggers.SecurityDemoLogger;


/**
 * The main controller class to be used by the client to perform operations
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
final public class SecurityDemoApplicationController {

	public static void main(String[] args) {
		
		SecurityDemoApplicationController securityDemoApplicationController = new SecurityDemoApplicationController();
	}
	
	/**
	 * The application context of Spring, please look {@link org.springframework.context.annotation.AnnotationConfigApplicationContext} 
	 */
	private AnnotationConfigApplicationContext annotationConfigApplicationContext;
	
	/**
	 * The custom Authentication Manager implemented by the demo app ,please look {@link com.kishore.spring.security.demo.authentication.manager.CustomAuthenticationManager}
	 */
	private AuthenticationManager authenticationManager = new CustomAuthenticationManager();
	
	/**
	 * The constructor which initializes the spring context
	 */
	public SecurityDemoApplicationController() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringSecurityDemoAppConfig.class);
	}
	
	/**
	 * The api which allows to login to the application
	 * @param userName The user's name
	 * @param password The user's password
	 * @return TODO
	 */
	public Boolean doLogin(String userName, char[] password) {
		 
		SecurityDemoLogger.SECURITYDEMOLOGGER.info("Initially Security context contains: " +SecurityContextHolder.getContext().getAuthentication());
		
		try {
		        Authentication request = new UsernamePasswordAuthenticationToken(userName, new String(password));
		        Authentication result = authenticationManager.authenticate(request);
		        SecurityDemoLogger.SECURITYDEMOLOGGER.info("Successfully authenticated");
		        SecurityContextHolder.getContext().setAuthentication(result);
		} catch(AuthenticationException e) {
				SecurityDemoLogger.SECURITYDEMOLOGGER.log(Level.SEVERE,"authentication failed , the error message is :"+e.getMessage());
				throw e;
		}
		SecurityDemoLogger.SECURITYDEMOLOGGER.info("Security context contains: " +SecurityContextHolder.getContext().getAuthentication());
		return Boolean.TRUE;
	}
	
	/**
	 * The api to access the bean's restricted api
	 * @return
	 */
	public String tryRestrictedMethodForProtectedBean() {
		return annotationConfigApplicationContext.getBean(ProtectedBean.class).onlyRoleUserCanAccess();
	}
	
	/**
	 * The api to access the bean's non restricted api
	 * @return
	 */
	public String tryNonRestrictedMethodForProtectedBean() {
		return annotationConfigApplicationContext.getBean(ProtectedBean.class).anyOneCanAccess();
	}
	
}
