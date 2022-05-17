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
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

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
        http.cors().configurationSource(request -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("http://localhost:4200", "http://example.com"));
            cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
            cors.setAllowedHeaders(List.of("*"));
            return cors;
        });
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(STATELESS);

        // permit all, non-secured
        http.authorizeRequests().antMatchers(POST,"/api/users").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/users/get/**").permitAll();
        http.authorizeRequests().antMatchers(POST,"/api/users/exists").permitAll();
        http.authorizeRequests().antMatchers(POST,"/api/order").permitAll();
        http.authorizeRequests().antMatchers(POST,"/api/otp").permitAll();
        http.authorizeRequests().antMatchers(GET,"/refreshtoken/**").permitAll();

        // users
        http.authorizeRequests().antMatchers(DELETE,"/api/users/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET,"/api/users/all").hasAnyAuthority("ADMIN","WH_MANAGER");
        http.authorizeRequests().antMatchers(PUT,"/api/users")
                .hasAnyAuthority("ADMIN","WH_MANAGER","BASIC","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(POST,"/api/users/fetch")
                .hasAnyAuthority("ADMIN","WH_MANAGER","BASIC","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");

        // warehouse
        http.authorizeRequests().antMatchers(GET,"/api/warehouse/get/**")
                .hasAnyAuthority("ADMIN","WH_MANAGER","BASIC","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(POST,"/api/warehouse")
                .hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET,"/api/warehouse/all")
                .hasAnyAuthority("ADMIN","SUPERVISOR","WH_MANAGER");
        http.authorizeRequests().antMatchers(DELETE,"/api/warehouse/**")
                .hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(PUT,"/api/warehouse")
                .hasAnyAuthority("ADMIN","WH_MANAGER","SUPERVISOR");

        // orders
        http.authorizeRequests().antMatchers(GET,"/api/order/all")
                .hasAnyAuthority("ADMIN","WH_MANAGER","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(GET,"/api/order/wh/**")
                .hasAnyAuthority("ADMIN","WH_MANAGER","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(GET,"/api/order/get/**")
                .hasAnyAuthority("ADMIN","WH_MANAGER","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(DELETE,"/api/order")
                .hasAnyAuthority("ADMIN","WH_MANAGER","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(PUT,"/api/order")
                .hasAnyAuthority("ADMIN","WH_MANAGER","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");

        // inventory
        http.authorizeRequests().antMatchers(GET,"/api/inventory/all")
                .hasAnyAuthority("ADMIN","WH_MANAGER","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(GET,"/api/inventory/get/**")
                .hasAnyAuthority("ADMIN","WH_MANAGER","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(POST,"/api/inventory")
                .hasAnyAuthority("ADMIN","WH_MANAGER","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(PUT,"/api/inventory")
                .hasAnyAuthority("ADMIN","WH_MANAGER","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(DELETE,"/api/inventory")
                .hasAnyAuthority("ADMIN","WH_MANAGER","SUPERVISOR");
        http.authorizeRequests().antMatchers(GET,"/api/inventory/wh/**")
                .hasAnyAuthority("ADMIN","WH_MANAGER","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");

        // otp,not secured

        // delivery notes
        http.authorizeRequests().antMatchers(GET,"/api/deliverynotes/get/**")
                .hasAnyAuthority("ADMIN","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(POST,"/api/deliverynotes")
                .hasAnyAuthority("ADMIN","WH_MANAGER","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(GET,"/api/deliverynotes/wh/**")
                .hasAnyAuthority("ADMIN","ADMIN","WH_MANAGER","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");
        http.authorizeRequests().antMatchers(PUT,"/api/deliverynotes")
                .hasAnyAuthority("ADMIN","DISPATCH_ASSOCIATE","WH_ASSOCIATE");

        //stats
        http.authorizeRequests().antMatchers(GET,"/api/stats/**")
                .hasAnyAuthority("ADMIN","WH_MANAGER","SUPERVISOR","DISPATCH_ASSOCIATE","WH_ASSOCIATE");

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
