package com.examples;

import org.apache.camel.spring.boot.FatJarRouter;
import org.apache.camel.spring.boot.FatWarInitializer;

/* This class allows to deploy the fat war to servers like Tomcat */
public class MyWarInitializer extends FatWarInitializer {

    @Override
    protected Class<? extends FatJarRouter> routerClass() {
        return MyRouter.class;
    }

}
