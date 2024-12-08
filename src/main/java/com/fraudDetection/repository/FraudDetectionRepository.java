package com.fraudDetection.repository;

import com.fraudDetection.entity.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

    @Repository
    public class FraudDetectionRepository {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        public AccountStatus fetchAccountStatus(String accountNumber, String ifscCode) {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("SP_NCRP_ACCOUNT_FETCH")
                    .returningResultSet("P_ACCT_RES", BeanPropertyRowMapper.newInstance(AccountStatus.class));

            Map<String, Object> result = jdbcCall.execute(
                    new MapSqlParameterSource()
                            .addValue("P_ACCOUNT_NUMBER", accountNumber)
                            .addValue("P_IFSC_CODE", ifscCode)
            );
            // Extract the result set
            List<AccountStatus> accountStatusList = (List<AccountStatus>) result.get("P_ACCT_RES");

            // Return the first result or null if the list is empty
            return accountStatusList.isEmpty() ? null : accountStatusList.get(0);
        }
    }
