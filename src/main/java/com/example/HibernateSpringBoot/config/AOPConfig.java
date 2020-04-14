package com.example.HibernateSpringBoot.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class AOPConfig {

	private static final Logger logger = LoggerFactory.getLogger(AOPConfig.class);
	
	@Around("execution(* com.example.HibernateSpringBoot.*.controller.*.*(..)) ||"
			+ "execution(* com.example.HibernateSpringBoot.*.daoimpl.*.*(..)) ||"
			+ "execution(* com.example.HibernateSpringBoot.*.serviceimpl.*.*(..))")
	
	public Object executeAroundEndPoint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		MethodSignature methodSign = (MethodSignature) proceedingJoinPoint.getSignature();
		String className= methodSign.getDeclaringType().getName();
		String methodName=methodSign.getName();
		final StopWatch stopWatch = new StopWatch();
        
        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        logger.info("-- IN AOP -- {} class {} method total execution time {} ms",className, methodName, stopWatch.getTotalTimeMillis());
		return result;
	}
}
