package com.gvdw.discogsviewer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Gullian Van Der Walt
 * Created at 14:29 on May, 2021
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Override and allow unauthenticated access everywhere - base authentication on sessionId.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");
    }
}
