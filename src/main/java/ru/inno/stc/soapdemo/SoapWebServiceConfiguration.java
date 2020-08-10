package ru.inno.stc.soapdemo;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

import java.io.IOException;

@EnableWs
@Configuration
public class SoapWebServiceConfiguration extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext){
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(applicationContext);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
    }

    @Bean(name = "calculator")
    public Wsdl11Definition wsdl11Definition() throws IOException {
        SimpleWsdl11Definition  simpleWsdl11Definition  = new SimpleWsdl11Definition();
        ClassPathResource classPathResource = new ClassPathResource("/wsdl/calculator.wsdl");
        System.out.println(classPathResource.getInputStream());

        simpleWsdl11Definition.setWsdl(classPathResource);
        System.out.println(simpleWsdl11Definition);
        return simpleWsdl11Definition;
    }


}