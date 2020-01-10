package com.javaretards.hairdresserapponintments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 *
 * @author mateusz
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("pass")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/dashboard/**","/services/**","/openhours/**","/clients/**","/day/**").hasRole("ADMIN")
                .and().formLogin().loginPage("/login").permitAll();
        
        //http.csrf().disable(); http.headers().frameOptions().disable(); //uncomment to acces /h2-console
    }
}
