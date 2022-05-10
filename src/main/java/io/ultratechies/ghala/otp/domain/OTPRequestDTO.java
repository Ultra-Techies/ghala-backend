package io.ultratechies.ghala.otp.domain;

import lombok.Data;

@Data
public class OTPRequestDTO {
    private String email;
    private String phoneNumber;
    private String name;
}
