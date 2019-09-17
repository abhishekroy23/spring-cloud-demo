package com.demo.registryservice.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Pointcut("within (com.demo.registryservice.controller..*)"+
			" || within (com.demo.registryservice.service..*)")
	private void applicationPointcut(){}
	
	@Around("applicationPointcut()")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

		logger.info("Enter {}.{} with argument(s) = {}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
				proceedingJoinPoint.getSignature().getName(), Arrays.toString(proceedingJoinPoint.getArgs()));

		Object result=null;
		try {
			result = proceedingJoinPoint.proceed();

		} catch (Throwable e) {
			logger.error("Error occured in aspect around of {}.{} with error = {}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
					proceedingJoinPoint.getSignature().getName(), e.getMessage(),e);
			throw e;
		}
		logger.info("Exit {}.{} with result(s) = {}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
				proceedingJoinPoint.getSignature().getName(), result);	
		return result;
	} 
	
	@AfterThrowing(pointcut = "applicationPointcut()", throwing = "exp")
	public void exceptionAdvice(JoinPoint joinPoint, Throwable exp) {
		logger.error("Error occured in aspect around of {}.{} with error = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), exp.getMessage(),exp);
	}
}
