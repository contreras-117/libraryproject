package com.libraryproject.springbootlibrary.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // Disable Cross Site Request Forgery
        httpSecurity.csrf().disable();

        // Protect endpoints at "/api/<type>/secure"
        httpSecurity.authorizeRequests(configurer ->
                configurer
                        .antMatchers("/api/books/secure/**")
                        .authenticated())
                .oauth2ResourceServer()
                .jwt();

        // Add CORS filters to our API endpoints so that our React app can pass the security config of our backend
        httpSecurity.cors();

        // Add content negotiation strategy
        httpSecurity.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());

        // Force Okta to return a 401 error status code for unauthenticated access
        Okta.configureResourceServer401ResponseBody(httpSecurity);

        return httpSecurity.build();
    }
}
