package io.ultratechies.ghala.otp.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.ultratechies.ghala.config.TwilioConfiguration;
import io.ultratechies.ghala.otp.domain.OTPRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SMSService {

    private final TwilioConfiguration twilioConfiguration;

    Map<String,String>otpMap=new HashMap<>();

    public ResponseEntity sendOTP(OTPRequestDTO otpRequestDTO){
            PhoneNumber to = new PhoneNumber(otpRequestDTO.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getPhoneNumber());
            String otp = generateOTP();
            String otpMessage = "Dear "+otpRequestDTO.getName()+", Hello from Ghala! Your OTP is " + otp + ". Use this Passcode to sign up. Thank You!";
            Message message = Message
                    .creator(to, from,
                            otpMessage)
                    .create();
            otpMap.put(otpRequestDTO.getEmail(), otp);
        return ResponseEntity.ok(otpMap);
    }

    //4 digit otp
    private String generateOTP() {
        return new DecimalFormat("0000")
                .format(new Random().nextInt(9999));
    }
}
