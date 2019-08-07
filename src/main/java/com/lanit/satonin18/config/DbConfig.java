package com.lanit.satonin18.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;
import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@ComponentScans( value = {
		@ComponentScan(
				basePackages = {"com.lanit.satonin18.app.dao"}
		)
		,
		@ComponentScan(
				basePackages = { "com.lanit.satonin18.app.service"}
		)
})
@EnableTransactionManagement
//@EnableJpaRepositories
@PropertySource("classpath:db.properties")
public class DbConfig {

	@Autowired
	private Environment env;//	private ApplicationContext context;

	@PostConstruct
	private void p() {
		System.out.println("pppppppppppppppppppppppppppppppppppppppp");
	}

	private Properties hibernateProperties() {
		Properties props = new Properties();

//		// Setting JDBC properties
//		props.put(DRIVER, env.getProperty("jdbc.driver"));
//		props.put(URL, env.getProperty("jdbc.url"));
//		props.put(USER, env.getProperty("jdbc.user"));
//		props.put(PASS, env.getProperty("jdbc.password"));

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

		return props;
	}

	@Bean //no DataSource
	public DriverManagerDataSource dataSource() {
		System.out.println("ddddddddddddddddddddddddddddddddddddd");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}

//	@Bean
//	public LocalSessionFactoryBean getSessionFactory() throws IOException {
//		System.out.println("ssssssssssssssssssssssssssssssssssssssssssss");
//
//		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
////		sessionFactoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
//
//		sessionFactoryBean.setDataSource(dataSource());
//		sessionFactoryBean.setHibernateProperties(hibernateProperties());
//
//		sessionFactoryBean.setPackagesToScan("com.lanit.satonin18.app.entity");
//		sessionFactoryBean.afterPropertiesSet();//
//
//		return sessionFactoryBean; //.getObject();
//	}

	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em_fb = new LocalContainerEntityManagerFactoryBean();

		em_fb.setDataSource(dataSource());
		em_fb.setJpaProperties(hibernateProperties());

		em_fb.setPackagesToScan("com.lanit.satonin18.app.entity");
		em_fb.setJpaVendorAdapter(jpaVendorAdapter());
		em_fb.afterPropertiesSet();//

//		return em_fb;
		return em_fb.getNativeEntityManagerFactory();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		return new HibernateJpaVendorAdapter();
	}
	//for @Transactional
	@Bean
	public PlatformTransactionManager transactionManager() throws IOException {
		System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");

//		return new HibernateTransactionManager(getSessionFactory().getObject());
//		return new JpaTransactionManager(getSessionFactory().getObject());

//		return new JpaTransactionManager(entityManagerFactory().getObject());
		return new JpaTransactionManager(entityManagerFactory());
	}
}