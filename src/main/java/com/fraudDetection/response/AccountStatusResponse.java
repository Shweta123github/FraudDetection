package com.fraudDetection.response;

import com.fraudDetection.entity.AccountStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AccountStatusResponse {
     int code;
     String statusCode;
     String message;
     AccountStatus data;

}
