package com.fraudDetection.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "NCRP_ACCOUNTS")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = false)
public class AccountStatus {

     @Id
     @Column(name = "ACCOUNT_NUMBER")  // Corrected column name
     String accountNumber;

     @Column(name = "IFSC_CODE")  // Corrected column name
     String ifscCode;

     @Column(name = "REPORTED_AS")  // Corrected column name
     String beneStatus;  // "B", "S", or "P"
}


