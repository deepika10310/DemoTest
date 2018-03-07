package com.edu.yati.auth.configuration;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class EducationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
	Logger logger = LoggerFactory.getLogger(EducationInitializer.class);
	
	public EducationInitializer() {
		super();
		logger.debug("EducationInitializer EducationInitializer EducationInitializer EducationInitializer");
	}
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { EducationConfiguration.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    @Override
    protected Filter[] getServletFilters() {
    	logger.debug("servlet filters");
    	Filter [] singleton = { new CORSFilter()};
    	return singleton;
    }
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	// TODO Auto-generated method stub
    	super.onStartup(servletContext);
    	//servletContext.addFilter("CorsFilter", new CORSFilter()).addMappingForUrlPatterns(null, false, "/");
    }
 
}