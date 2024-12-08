package com.fraudDetection.controller;
import com.fraudDetection.constants.StatementErrorMessages;
import com.fraudDetection.entity.AccountStatus;
import com.fraudDetection.request.AccountStatusRequest;
import com.fraudDetection.response.AccountStatusResponse;
import com.fraudDetection.service.FraudDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/fraudDetection")
public class FraudDetectionController {

        @Autowired
        private FraudDetectionService fraudDetectionService;

        @PostMapping("/fetchAccountStatus")
        public ResponseEntity<AccountStatusResponse> fetchAccountStatus(@RequestBody(required = false) AccountStatusRequest request) {
            if (request == null) {
                return ResponseEntity.badRequest().body(
                        new AccountStatusResponse(
                                StatementErrorMessages.BAD_REQUEST.getStatus().value(),
                                "E400",
                                "Request body is missing",
                                null
                        )
                );
            }
            AccountStatus accountStatus = fraudDetectionService.getAccountStatus(request.getAccountNumber(), request.getIfsc());

            return ResponseEntity.ok(new AccountStatusResponse(
                    StatementErrorMessages.SUCCESS.getStatus().value(),
                    "S101",
                    StatementErrorMessages.SUCCESS.getMessage(),
                    accountStatus
            ));
        }
    }


//HTTP 200 with a list of AccountStatus if data is found.
//HTTP 400 if the request body is missing.