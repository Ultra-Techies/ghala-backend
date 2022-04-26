package io.ultratechies.ghala.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
@NoArgsConstructor
public class TwilioConfiguration {
    private String twilioAccountSid;
    private String phoneNumber;
    private String twilioAuthToken;
}
