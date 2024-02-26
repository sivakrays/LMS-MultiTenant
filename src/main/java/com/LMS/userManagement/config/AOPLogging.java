/*
package com.LMS.userManagement.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AOPLogging {



      private Logger log=LoggerFactory.getLogger(AOPLogging.class);

      @Before(value = "execution(* com.LMS.userManagement.controller.*.*(..))")
      public void logStatementBefore(JoinPoint joinPoint) {
      log.info("Executing {}",joinPoint.getArgs());
      log.info("Executing {}",joinPoint.getSignature());
      log.debug("Executing {}",joinPoint);
   }

      @After(value = "execution(* com.LMS.userManagement.controller.*.*(..))")
      public void logStatementAfter(JoinPoint joinPoint) {
      log.info("Complete exceution of {}",joinPoint.getArgs());
         log.info("Complete {}",joinPoint.getArgs());
   }

      @Around(value = "execution(* com.LMS.userManagement.service.*.*(..))")
      public Object taskHandler(ProceedingJoinPoint joinPoint) throws Throwable {

      try {
         Object obj=joinPoint.proceed();
         return obj;
      }
      catch(Exception e) {
         log.info("TaskException Message {}",e.getMessage());
         throw e;
      }
   }

      @Around(value = "execution(* com.LMS.userManagement.controller.*.*(..))")
      public Object timeTracker(ProceedingJoinPoint joinPoint) throws Throwable {

      long stratTime=System.currentTimeMillis();

      try {
         Object obj=joinPoint.proceed();
         long timeTaken=System.currentTimeMillis()-stratTime;
         log.info("Time taken by {} is {}",joinPoint,timeTaken);
         return obj;
      }
      catch(Exception e) {
         log.info("TaskException Message {}",e.getMessage());
         throw e;
      }
   }

   }

*/
