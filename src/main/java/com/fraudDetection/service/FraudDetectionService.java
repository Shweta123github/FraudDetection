package com.fraudDetection.service;

import com.fraudDetection.entity.AccountStatus;
import com.fraudDetection.repository.FraudDetectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FraudDetectionService {

    @Autowired
    private FraudDetectionRepository fraudDetectionRepository;

    @Transactional(readOnly = false)
    public AccountStatus getAccountStatus(String accountNumber, String ifsc) {
        return fraudDetectionRepository.fetchAccountStatus(accountNumber, ifsc);
    }
}



