package io.ultratechies.ghala;

import com.twilio.Twilio;
import io.ultratechies.ghala.config.SwaggerConfiguration;
import io.ultratechies.ghala.config.TwilioConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableSwagger2
@EnableWebSecurity
@Import(SwaggerConfiguration.class)
@ComponentScan("io.ultratechies.ghala")
@RequiredArgsConstructor
@EnableScheduling
public class GhalaApplication {

	@Autowired
	private final TwilioConfiguration twilioConfiguration;

	@PostConstruct
	public void innitTwilio(){
		Twilio.init(twilioConfiguration.getTwilioAccountSid(),twilioConfiguration.getTwilioAuthToken());
	}

	public static void main(String[] args) {
		SpringApplication.run(GhalaApplication.class, args);
	}

}
