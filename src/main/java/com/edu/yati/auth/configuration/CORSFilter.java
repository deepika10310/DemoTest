package com.edu.yati.auth.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.GenericFilterBean;

@WebFilter("/*")
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter extends GenericFilterBean {
	Logger logger = LoggerFactory.getLogger(CorsFilter.class);
	public CORSFilter() {
		logger.debug("CORSFilter CORSFilter CORSFilter");
	}
	
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		logger.debug("................................... Filering on ..................... ");
		HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", 
        		"X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers, Access-Control-Allow-Origin");
        
        logger.debug("request method ...." + ((HttpServletRequest) req).getMethod());
        logger.debug("request uri...." + ((HttpServletRequest) req).getRequestURL());
        
        try {
        	if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                chain.doFilter(req, res);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            // somehow handle it, like display an error message
        }
	}

	//public void init(FilterConfig filterConfig) {}

	public void destroy() {}

}