package io.ultratechies.ghala.otp.rest;

import io.ultratechies.ghala.otp.domain.OTPRequestDTO;
import io.ultratechies.ghala.otp.service.SMSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("api/otp")
@RequiredArgsConstructor
public class OTPController {
    private final SMSService smsService;

    @PostMapping
    public ResponseEntity sendOtp(@RequestBody OTPRequestDTO otpRequestDTO){
        return smsService.sendOTP(otpRequestDTO);
    }
}
