package com.javaretards.hairdresserapponintments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

/**
 *
 * @author mateusz
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Value("${userdata.username}")
    private String username;

    @Value("${userdata.password}")
    private String password;

    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/dashboard/**","/services/**","/openhours/**","/clients/**","/day/**").hasRole("ADMIN")
                .and().formLogin().loginPage("/login").permitAll();

        http.csrf().requireCsrfProtectionMatcher(new AndRequestMatcher(CsrfFilter.DEFAULT_CSRF_MATCHER, new RegexRequestMatcher("^(?!/api/)", null)));
    }
}
