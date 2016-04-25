/**
 * 
 */
package com.kishore.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.kishore.spring.security.demo.beans.ProtectedBean;

/**
 * The demo app config class which governs the different spring managed bean and security applied on that
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
@Configuration
@ImportResource(value={"security.xml"}) 
public class SpringSecurityDemoAppConfig {

	/**
	 * The {@link com.kishore.spring.security.demo.beans.ProtectedBean} registered as bean to spring container
	 * @return {@link com.kishore.spring.security.demo.beans.ProtectedBean} bean registered as bean to spring container
	 */
	@Bean
	public ProtectedBean protectedBean() {
		return new ProtectedBean();
	}
}
