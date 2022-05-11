package io.ultratechies.ghala.security;

import io.ultratechies.ghala.security.filter.CustomAuthenticationFilter;
import io.ultratechies.ghala.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/api/users","/refreshtoken").permitAll();
        http.authorizeRequests().antMatchers(POST,"/api/users").permitAll();
        http.authorizeRequests().antMatchers(POST,"/api/users/exists").permitAll();
        http.authorizeRequests().antMatchers(POST,"/api/otp").permitAll();
        http.authorizeRequests().antMatchers(POST,"/api/order").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/warehouse/**").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/users").permitAll();//
        http.authorizeRequests().antMatchers(PUT,"/api/users").permitAll();
        http.authorizeRequests().antMatchers(DELETE,"/api/users").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET,"/api/users/all").hasAnyAuthority("ADMIN","WH_MANAGER");
        http.authorizeRequests().antMatchers(POST,"/api/users/fetch")
                .hasAnyAuthority("ADMIN","WH_MANAGER","BASIC","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(POST,"/api/warehouse")
                .hasAnyAuthority("ADMIN","SUPERVISOR");
        http.authorizeRequests().antMatchers(DELETE,"/api/warehouse")
                .hasAnyAuthority("ADMIN","SUPERVISOR");
        http.authorizeRequests().antMatchers(PUT,"/api/warehouse")
                .hasAnyAuthority("ADMIN","SUPERVISOR");
        http.authorizeRequests().antMatchers(GET,"/api/order/all")
                .hasAnyAuthority("ADMIN","SUPERVISOR");
        http.authorizeRequests().antMatchers(GET,"/api/order/wh/**")
                .hasAnyAuthority("ADMIN","WH_MANAGER","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(GET,"/api/order")
                .hasAnyAuthority("ADMIN","WH_MANAGER","BASIC","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
