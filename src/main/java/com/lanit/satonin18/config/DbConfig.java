package com.lanit.satonin18.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.Properties;
import static org.hibernate.cfg.AvailableSettings.*;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_STATEMENTS;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class DbConfig {

	@Autowired
	private Environment env;//	private ApplicationContext context;

   @PostConstruct
   private void p() {
      System.out.println("pppppppppppppppppppppppppppppppppppppppp");
   }

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		System.out.println("ssssssssssssssssssssssssssssssssssssssssssss");
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

//		factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
		Properties props = new Properties();
		// Setting JDBC properties
		props.put(DRIVER, env.getProperty("mysql.driver"));
		props.put(URL, env.getProperty("mysql.url"));
		props.put(USER, env.getProperty("mysql.user"));
		props.put(PASS, env.getProperty("mysql.password"));
		// Setting Hibernate properties
		props.put(DIALECT, env.getProperty("hibernate.dialect"));
		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		props.put(FORMAT_SQL, env.getProperty("hibernate.format_sql"));
		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
		// Setting C3P0 properties
		props.put(C3P0_MIN_SIZE,
				env.getProperty("hibernate.c3p0.min_size"));
		props.put(C3P0_MAX_SIZE,
				env.getProperty("hibernate.c3p0.max_size"));
		props.put(C3P0_ACQUIRE_INCREMENT,
				env.getProperty("hibernate.c3p0.acquire_increment"));
		props.put(C3P0_TIMEOUT,
				env.getProperty("hibernate.c3p0.timeout"));
		props.put(C3P0_MAX_STATEMENTS,
				env.getProperty("hibernate.c3p0.max_statements"));

		factoryBean.setHibernateProperties(props);

		factoryBean.setPackagesToScan("com.lanit.satonin18.app.entity");
//		factoryBean.setAnnotatedClasses(
//				ActionType.class,
//				NotificationStatus.class,
//
//				Action.class,
//				Notification.class,
//
//				Organization.class,
//				User.class
//		);
		return factoryBean;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
}