package io.ultratechies.ghala;

import io.ultratechies.ghala.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableSwagger2
@Import(SwaggerConfiguration.class)
public class GhalaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GhalaApplication.class, args);
	}

}
