package com.javaretards.hairdresserapponintments;

import com.javaretards.hairdresserapponintments.Config.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HairdresserApponintmentsApplication {
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.addInitParameter("GET","(whoami|dashboard(/[0-9\\-]+)?|day(/[0-9\\-]+)?)");
        registrationBean.addInitParameter("POST","(services|openhours)");  //(services(/\d+)?)
        registrationBean.addInitParameter("DELETE","(services/\\d+|openhours/\\d+)");
        registrationBean.addInitParameter("PATCH","(day(/[0-9\\-]+)?)");
        return registrationBean;
    }
    public static void main(String[] args) {
        SpringApplication.run(HairdresserApponintmentsApplication.class, args);
    }
}
