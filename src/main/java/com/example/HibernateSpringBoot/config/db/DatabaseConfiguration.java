package com.example.HibernateSpringBoot.config.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration	// Analogue to beans tag
@EnableTransactionManagement
public class DatabaseConfiguration {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private DataSource dataSource;
	
	
	public DataSource getDataSource(){
		DriverManagerDataSource  ds = new DriverManagerDataSource();
		/*ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/practice_schema");
		ds.setUsername("root");
		ds.setPassword("welcome");*/
		String driverName=env.getProperty("db.driver-class-name");
		String url=env.getProperty("db.url");
		String userName=env.getProperty("db.user-name");
		String password=env.getProperty("db.password");
		System.out.println("driver Name "+driverName);
		ds.setDriverClassName(driverName);
		ds.setUrl(url);
		ds.setUsername(userName);
		ds.setPassword(password);
		
		return ds;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory(){
		System.out.println("datasource "+dataSource.getClass());
		LocalSessionFactoryBean  sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("com.example.HibernateSpringBoot");  // used to Mapping Hibernate Pojo classes
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hibernateProperties.put("hibernate.show_sql", true);
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager(){
		HibernateTransactionManager tx = new HibernateTransactionManager();
		tx.setSessionFactory(getSessionFactory().getObject());
		return tx;
	}

}
