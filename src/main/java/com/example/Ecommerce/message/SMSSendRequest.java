package com.example.Ecommerce.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SMSSendRequest {

    private String destinationSMSNumber;

    private String smsMessages;
}
