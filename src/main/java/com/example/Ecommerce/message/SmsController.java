package com.example.Ecommerce.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/msg")
@Slf4j
public class SmsController {

    @Autowired
    SMSService smsService;
    @PostMapping("/processSMS")
    public String processSMS(@RequestBody SMSSendRequest smSsendRequest){
        log.info("processSMS started sendRequest: "+ smSsendRequest.toString());
        return smsService.sendSMS(smSsendRequest.getDestinationSMSNumber(),smSsendRequest.getSmsMessages());
    }

    @PostMapping("/processWhatsapp")
    public String processWhatsapp(@RequestBody SMSSendRequest smSsendRequest){
        log.info("processSMS started sendRequest: "+ smSsendRequest.toString());
        return smsService.sendWhatsapp(smSsendRequest.getDestinationSMSNumber(),smSsendRequest.getSmsMessages());
    }
}
