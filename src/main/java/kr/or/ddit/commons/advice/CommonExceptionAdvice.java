package kr.or.ddit.commons.advice;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class CommonExceptionAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	@AfterThrowing(pointcut="execution(* kr.or.ddit..service.*.*(..))",throwing="e")
	public void afterThrow(Throwable e) throws Throwable {
		LOGGER.error("===================",e);
		throw e;
	}
}
