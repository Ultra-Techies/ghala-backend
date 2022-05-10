package io.ultratechies.ghala.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebSecurityConfigurerAdapter {
    public static String[] SWAGGER_URL_PATHS = new String[] { "/swagger-ui.html**", "/swagger-resources/**",
            "/v2/api-docs**", "/webjars/**" };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.requestMatchers().antMatchers(SWAGGER_URL_PATHS).and().authorizeRequests().antMatchers(SWAGGER_URL_PATHS)
                .permitAll();
    }

    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2).ignoredParameterTypes(AuthenticationPrincipal.class).select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("io.ultratechies.ghala")))
                .paths(PathSelectors.any()).build();
    }

}