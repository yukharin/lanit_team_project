package com.lanit.satonin18.config;

//import com.lanit.satonin18.config.security.WebSecurityConfig;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

public class DispatcherServletInit
        extends AbstractAnnotationConfigDispatcherServletInitializer {

   @Override
   public void onStartup(ServletContext servletContext) throws ServletException {
      //create the root Spring application context

      AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
      rootContext.register(DbConfig.class
//              ,
//              WebSecurityConfig.class
      );

      //Where CentralServerConfigurationEntryPoint.class must only scan components that must work in the server side
      // (@Service, @Repository, @Configuration for Transaction, Hibernate, DataSource etc)

      servletContext.addListener(new ContextLoaderListener(rootContext));

      //Create the dispatcher servlet's Spring application context

      AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
      servletAppContext.register(WebMvcConfig.class);

      //Where CentralWebConfigurationEntryPoint must only scan components that must work in the client/web side
      // (@Controller, @Configuration for Formatters, Tiles, Converters etc)

      DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
      // throw NoHandlerFoundException to controller ExceptionHandler.class. Used for <error-page> analogue
      dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

      //register and map the dispatcher servlet
      //note Dispatcher servlet with constructor arguments

      ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
      dispatcher.setLoadOnStartup(1);
      dispatcher.addMapping("/");

      FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filterForm", new CharacterEncodingFilter());
      encodingFilter.setInitParameter("encoding", "UTF-8");
      encodingFilter.setInitParameter("forceEncoding", "true");
      encodingFilter.addMappingForUrlPatterns(null, true, "/*");
   }

//   @Override
//   protected FilterForm[] getServletFilters() {
//      CharacterEncodingFilter cef = new CharacterEncodingFilter();
//      cef.setEncoding("UTF-8");
//      cef.setForceEncoding(true);
//      return new FilterForm[]{new HiddenHttpMethodFilter(), cef};
//   }

   @Override
   protected Class<?>[] getRootConfigClasses() {
      return new Class[] {DbConfig.class
//              ,
//              WebSecurityConfig.class
      };
   }

   @Override
   protected String[] getServletMappings() {
      return new String[0];
   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class<?>[0];
   }
}
