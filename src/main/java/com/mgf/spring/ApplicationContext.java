package com.mgf.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Maciek on 04-Jul-15.
 */
public class ApplicationContext {
    private static org.springframework.context.ApplicationContext applicationContext;

    public static org.springframework.context.ApplicationContext getApplicationContext() {
        if(applicationContext == null)
            applicationContext = new AnnotationConfigApplicationContext(Configuration.class);
        return applicationContext;

    }
    private ApplicationContext() {
    }
}
