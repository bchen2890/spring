package com.binhui.example.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.FixedThemeResolver;
import org.springframework.web.servlet.theme.SessionThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"carmelo.spring.controller"})
public class ThemeConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Bean
	public ResourceBundleThemeSource themeSource() {
		ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
		themeSource.setBasenamePrefix("themes.theme-");
		return themeSource;
	}

	@Bean
	public CookieThemeResolver cookiethemeResolver() {
		CookieThemeResolver resolver = new CookieThemeResolver();
		resolver.setDefaultThemeName("dark");
		resolver.setCookieName("cookie-theme");
		return resolver;
	}

	@Bean
	public ThemeChangeInterceptor themeChangeInterceptor() {
		ThemeChangeInterceptor interceptor = new ThemeChangeInterceptor();
		interceptor.setParamName("mytheme");
		return interceptor;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(themeChangeInterceptor());
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	@Bean
	public FixedThemeResolver fixedThemeResolver() {
		FixedThemeResolver resolver = new FixedThemeResolver();
		resolver.setDefaultThemeName("light");
		return resolver;
	}

	@Bean
	public SessionThemeResolver sessionThemeResolver() {
		SessionThemeResolver resolver = new SessionThemeResolver();
		resolver.setDefaultThemeName("dark");
		return resolver;
	}
}
