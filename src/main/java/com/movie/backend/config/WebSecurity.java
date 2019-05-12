package com.movie.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
    * Configure database authentication
    * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Temprory authentication => Static authentication
        /*auth.inMemoryAuthentication()
                .withUser(User.withUsername("haytham").password("{noop}toortoor").roles("ADMIN", "MANAGER"))
                .withUser(User.withUsername("imrane").password("{noop}toortoor").roles("SUPERADMIN", "ADMIN"));*/
        // Set database authentitcation using UserDetailsService which is defined in the services section
        auth.userDetailsService(this.userDetailsService).passwordEncoder(this.bCryptPasswordEncoder);
    }

    /*
    * Ignore static files from spring security protection
    * */
    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/media**", "/css**", "/js**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // JWT authentication
        // csrf must be disabled
        // We will create a STATELESS SessionCreationPolicy
        // We are disabling session authetication (As all use in any website)
        // We need to create a filter as a class named in our case: JWTAuthenticationFIlter
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                // We authorize media, access-denied, login and register only for direct access
                // (For displaying access denied and access to server resource from Front-end application)
                .antMatchers("/media/**", "/access-denied", "/login", "/register")
                .permitAll()
                .antMatchers("/api/**").hasAuthority("ROLE_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
        // Add the created filter to the http object
        // this.authenticationManager() is an inherited method from super class ||
        // addFilterAfter to check if the associated token with the user is authenticated or not
        http.addFilter(new JWTAuthenticationFilter(this.authenticationManager()))
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
