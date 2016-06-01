/**
 * 
 */
package com.kishore.pluggable.repository;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kishore.repository.entities.ConfigEntity;
import com.kishore.repository.entities.GenericEntity;
import com.kishore.repository.framework.PluggableRepositoryRegistry;
import com.kishore.repository.service.RepositoryService;
import com.kishore.repository.service.impl.DBRepositoryServiceImpl;
import com.kishore.repository.service.impl.LDAPRepositoryServiceImpl;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class TestPluggableLDAPRepository {

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
	 * Test method for {@link com.kishore.repository.framework.PluggableRepositoryRegistry#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		PluggableRepositoryRegistry pluggableRepositoryFramework = PluggableRepositoryRegistry.getInstance();
		PluggableRepositoryRegistry anotherpluggableRepositoryFramework = PluggableRepositoryRegistry.getInstance();
		assertTrue("This in not singleton", pluggableRepositoryFramework == anotherpluggableRepositoryFramework);
	}

	/**
	 * Test method for {@link com.kishore.repository.framework.PluggableRepositoryRegistry#repositoryServiceInstance()}.
	 */
	@Test
	public void testRepositoryInstance() {
		PluggableRepositoryRegistry pluggableRepositoryFramework = PluggableRepositoryRegistry.getInstance();
		RepositoryService repositoryService =  pluggableRepositoryFramework.repositoryServiceInstance();
		assertNotNull("No repository service registered", repositoryService);
		assertTrue("RepositoryInstance is not LDAPRepository", repositoryService instanceof LDAPRepositoryServiceImpl);
	}
	
	@Test
	public void testDBRepositoryCommand() {
		PluggableRepositoryRegistry pluggableRepositoryFramework = PluggableRepositoryRegistry.getInstance();
		RepositoryService repositoryService =  pluggableRepositoryFramework.repositoryServiceInstance();
		assertNotNull("No repository service registered", repositoryService);
		assertTrue("RepositoryInstance is not LDAPRepository", repositoryService instanceof LDAPRepositoryServiceImpl);
		ConfigEntity configEntity = new ConfigEntity("1","2");
		/*genericEntity.putAttribute("someAttribute1", "1");
		genericEntity.putAttribute("someAttribute2", "2");*/
		try {
			repositoryService.create(configEntity);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Creation failed");
		}
		
		
	}

}
