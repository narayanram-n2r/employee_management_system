package com.rps.capestone.batch11.config;

import com.rps.capestone.batch11.jwtutils.JwtToken;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {
    private JwtToken jwtToken;

    public JwtConfigurer(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter customFilter = new JwtAuthenticationFilter(jwtToken);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
