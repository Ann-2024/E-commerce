package com.example.Ecommerce.message;

import com.example.Ecommerce.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SMSService {

    @Autowired
    TwilioConfig twilioConfig;

    @PostConstruct
    private void setUp() {
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }

    public String sendSMS(String smsNumber, String smsMessage) {
        try {
            Message message = Message.creator(
                    new PhoneNumber(smsNumber),
                    new PhoneNumber(twilioConfig.getPhoneNumber()),
                    smsMessage).create();

            return message.getStatus().toString();
        } catch (ApiException e) {
            log.error("Failed to send SMS: " + e.getMessage());
            return "Failed to send SMS: " + e.getMessage();
        }
    }

    public String sendWhatsapp(String smsNumber, String smsMessage) {
        try {
            Message message = Message.creator(
                    new PhoneNumber("whatsapp:" + smsNumber),
                    new PhoneNumber("whatsapp:" + twilioConfig.getWhatsappNumber()),
                    smsMessage).create();

            return message.getStatus().toString();
        } catch (ApiException e) {
            log.error("Failed to send WhatsApp message: " + e.getMessage());
            return "Failed to send WhatsApp message: " + e.getMessage();
        }
    }
}
