package com.corp.workflows.design;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Servlet initializer to make sure that the application is properly started
 * as a war (the embedded tomcat is then not loaded) from an application container
 *
 * @author Filip Hrisafov
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CorpWorkflowsDesignApplication.class);
	}

}
