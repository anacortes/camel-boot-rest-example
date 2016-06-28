package com.examples;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyRouter extends FatJarRouter {
    @Autowired
    private MyConfiguration config;

    @Override
    public void configure() {
        from("servlet:/hello?servletName=CamelServlet")
                .setBody(simple("Hello world!!! My name is " + config.getName()))   //{{my-example.name}}
        ;
    }

    @Bean
    public ServletRegistrationBean camelServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/*");
        registration.setName("CamelServlet");
        return registration;
    }
}
