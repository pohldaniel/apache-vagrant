package de.app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import de.app.filter.PublicAPIFilter;

public class FilterInitializer {
	
	@Bean(name="publicAPI")
	public FilterRegistrationBean<PublicAPIFilter> publicAPIFilterRegistration() {	
		FilterRegistrationBean<PublicAPIFilter> registration = new FilterRegistrationBean<PublicAPIFilter>();
		registration.setFilter(new PublicAPIFilter());
		registration.addUrlPatterns( "/api/*");
		return registration;
	}
}
