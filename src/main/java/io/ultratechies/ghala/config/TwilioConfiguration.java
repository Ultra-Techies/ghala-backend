package io.ultratechies.ghala.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
public class TwilioConfiguration {
    private String accountSid;
    private String phoneNumber;
    private String authToken;
}
