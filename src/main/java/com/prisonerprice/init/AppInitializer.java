package com.prisonerprice.init;

import com.prisonerprice.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication(scanBasePackages = {"com.prisonerprice"})
@ServletComponentScan(basePackages = {"com.prisonerprice.filter"})
public class AppInitializer {
    public static void main(String[] args) throws NullPointerException{
        if (HibernateUtil.getSessionFactory() == null) {
            throw new NullPointerException("The Hibernate session factory is NULL!");
        }
        SpringApplication.run(AppInitializer.class, args);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }
}
