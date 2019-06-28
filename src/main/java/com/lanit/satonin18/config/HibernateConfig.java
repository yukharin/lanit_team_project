package com.lanit.satonin18.config;

import com.lanit.satonin18.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//TODO REPLACE HARDCODE(frpm package @config@) such as "com.lanit.satonin18" and "com.lanit.satonin18.model" IN PROPERTY


@Configuration
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.lanit.satonin18")})
public class HibernateConfig {

	@Autowired
	private ApplicationContext context;

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
		factoryBean.setPackagesToScan("com.lanit.satonin18.model");
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
