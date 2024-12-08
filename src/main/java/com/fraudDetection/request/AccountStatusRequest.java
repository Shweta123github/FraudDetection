package com.fraudDetection.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountStatusRequest {
     String accountNumber;
     String ifsc;
     String upiId;
     String mobileNo;
     String paymentType;
     String name;
     String nickName;
     String freeField1;
     String freeField2;
     String freeField3;
     String freeField4;
     String freeField5;
}
