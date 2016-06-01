/**
 * 
 */
package com.kishore.repository.framework;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kishore.repository.provider.RepositoryProvider;
import com.kishore.repository.service.RepositoryService;

/**
 * The class provides the registry mechanism to hook in RepositoryProvider
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class PluggableRepositoryRegistry {

	ApplicationContext context =  null;
	/**
	 * The dynamic repository provider name to be hooked in during jvm startup
	 */
	private static final String CUSTOM_REPOSITORY_PROVIDER = System.getProperty("custom.repository.provider");
	
	//private Map<String,RepositoryProvider> mapProviders = new LinkedHashMap<String, RepositoryProvider>(4);
	private Map<String,RepositoryProvider> mapProviders = null;
	
	/**
	 * The private constructor created to allow only one instance of this class
	 */
	private PluggableRepositoryRegistry() {
			context =  new ClassPathXmlApplicationContext("pluggable-repository-framework.xml");
			
			/*registerRepositoryProvider(InMemoryRepositoryProvider.getInstance());
			registerRepositoryProvider(DBRepositoryProvider.getInstance());
			registerRepositoryProvider(LDAPRepositoryProvider.getInstance());*/
			
			mapProviders = (Map<String,RepositoryProvider>) context.getBean("registeredProviders");
			if (CUSTOM_REPOSITORY_PROVIDER != null && !"".equals(CUSTOM_REPOSITORY_PROVIDER)) {
				registerRepositoryProvider(CUSTOM_REPOSITORY_PROVIDER);
			}
	}
	
	
	/**
	 * The private inner class acting as a helper to create the singleton 
	 * @since Release1
	 */
	private static class PluggableRepositoryFrameworkSingletonCreator {
		/**
		 *  The static private pluggableRepositoryFramework
		 */
		private static PluggableRepositoryRegistry pluggableRepositoryFramework = new PluggableRepositoryRegistry();
	}
	
	/**
	 * Returns the singleton {@link PluggableRepositoryRegistry}
	 * @return The singleton {@link PluggableRepositoryRegistry}
	 */
	public static PluggableRepositoryRegistry getInstance() {
		return PluggableRepositoryFrameworkSingletonCreator.pluggableRepositoryFramework;
	}
	
	
	
	/**
	 * The api to register the {@link RepositoryProvider}
	 * @param repositoryProvider , The {@link RepositoryProvider} to be registered
	 */
	public void registerRepositoryProvider(RepositoryProvider repositoryProvider) {
		mapProviders.put(repositoryProvider.getClass().getName(), repositoryProvider);
	}
	
	/**
	 * Registers the provider given fully qualified class name present in classpath, the class must be implementation of {@link RepositoryProvider} and must have default constructor
	 * @param repositoryProviderClassName The repository provider class name
	 */
	public void registerRepositoryProvider(String repositoryProviderClassName) {
		try {
			mapProviders.put(repositoryProviderClassName,(RepositoryProvider) (Class.forName(repositoryProviderClassName).newInstance()));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the first registered {@link RepositoryProvider}, if jvm argument is supplied for custom repository then custom repository provider will be returned
	 * @return The registered {@link RepositoryProvider}
	 */
	public RepositoryService repositoryServiceInstance() {
		return (CUSTOM_REPOSITORY_PROVIDER != null && !"".equals(CUSTOM_REPOSITORY_PROVIDER)) ?	mapProviders.get(CUSTOM_REPOSITORY_PROVIDER).createRepositoryService() : mapProviders.values().iterator().next().createRepositoryService();
	}
	
}
