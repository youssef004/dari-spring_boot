package tn.esprit.spring;

import java.io.File;
import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import tn.esprit.spring.Config.FileStorageProperties;
import tn.esprit.spring.controller.FileUploadController;


@EnableAutoConfiguration
@Configuration
@ComponentScan
@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class DariSpringBootApplication {
//	@Bean
//	public ServletRegistrationBean servletRegistrationBean() {
//	FacesServlet servlet = new FacesServlet();
//	return new ServletRegistrationBean(servlet, "*.jsf"); }
//	
	
	public static void main(String[] args)throws Exception  {
		
		
		new File(FileUploadController.uploadDirectory).mkdir();
		
		SpringApplication.run(DariSpringBootApplication.class, args);
		//SpringApplication.run(FileApplication.class,args);
	}
	
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		FacesServlet servlet = new FacesServlet();
		return new ServletRegistrationBean(servlet, "*.jsf");
	}
	
	
	@Bean
	public FilterRegistrationBean rewriteFilter() {
		FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
		rwFilter.setDispatcherTypes(
				EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR));
		rwFilter.addUrlPatterns("/*");
		return rwFilter;
	}

}
