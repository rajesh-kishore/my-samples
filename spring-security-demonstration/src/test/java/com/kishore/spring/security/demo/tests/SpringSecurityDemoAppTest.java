/**
 * 
 */
package com.kishore.spring.security.demo.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kishore.spring.security.demo.controllers.SecurityDemoApplicationController;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class SpringSecurityDemoAppTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.kishore.spring.security.demo.controllers.SecurityDemoApplicationController#doLogin(java.lang.String, char[])}.
	 */
	@Test
	public void testDoLogin() {
		SecurityDemoApplicationController securityDemoApplicationController = new SecurityDemoApplicationController(); 
		assertTrue(" The user could not be authenticated.",securityDemoApplicationController.doLogin("masteruser", new char[]{'m','a','s','t','e','r','u','s','e','r'}));
	}

	/**
	 * Test method for {@link com.kishore.spring.security.demo.controllers.SecurityDemoApplicationController#tryRestrictedMethodForProtectedBean()}.
	 */
	@Test
	public void testTryRestrictedMethodForProtectedBean() {
		SecurityDemoApplicationController securityDemoApplicationController = new SecurityDemoApplicationController(); 
		assertTrue(" The user could not be authenticated.",securityDemoApplicationController.doLogin("masteruser", new char[]{'m','a','s','t','e','r','u','s','e','r'}));
		assertEquals("onlyRoleUserCanAccess", securityDemoApplicationController.tryRestrictedMethodForProtectedBean());
	}
	
	/**
	 * Test method for {@link com.kishore.spring.security.demo.controllers.SecurityDemoApplicationController#tryRestrictedMethodForProtectedBean()}.
	 */
	@Test
	public void testTryRestrictedMethodForProtectedBeanByGuestUser() {
		SecurityDemoApplicationController securityDemoApplicationController = new SecurityDemoApplicationController(); 
		assertTrue(" The user could not be authenticated.",securityDemoApplicationController.doLogin("guestuser", new char[]{'g','u','e','s','t','u','s','e','r'}));
		Boolean exceptionOccured = Boolean.FALSE;
		try {
			securityDemoApplicationController.tryRestrictedMethodForProtectedBean();
		} catch (Exception e) {
			exceptionOccured = Boolean.TRUE;
		}
		assertEquals(Boolean.TRUE,exceptionOccured);
	}

	/**
	 * Test method for {@link com.kishore.spring.security.demo.controllers.SecurityDemoApplicationController#tryNonRestrictedMethodForProtectedBean()}.
	 */
	@Test
	public void testTryNonRestrictedMethodForProtectedBean() {
		SecurityDemoApplicationController securityDemoApplicationController = new SecurityDemoApplicationController(); 
		assertTrue(" The user could not be authenticated.",securityDemoApplicationController.doLogin("guestuser", new char[]{'g','u','e','s','t','u','s','e','r'}));
		Boolean exceptionOccured = Boolean.FALSE;
		try {
			securityDemoApplicationController.tryNonRestrictedMethodForProtectedBean();
		} catch (Exception e) {
			exceptionOccured = Boolean.TRUE;
		}
		assertEquals(Boolean.FALSE,exceptionOccured);
	
	}

}
