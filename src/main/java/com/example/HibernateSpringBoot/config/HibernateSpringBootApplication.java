package com.example.HibernateSpringBoot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(	exclude=HibernateJpaAutoConfiguration.class)
@ComponentScan("com.example.HibernateSpringBoot")
public class HibernateSpringBootApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(HibernateSpringBootApplication.class, args);
		for(String beanName: ctx.getBeanDefinitionNames()){
			System.out.println("Bean Name "+beanName);
		}
	}

}
