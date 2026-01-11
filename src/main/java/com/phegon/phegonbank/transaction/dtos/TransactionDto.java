package com.phegon.phegonbank.transaction.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.phegon.phegonbank.account.dtos.AccountDto;
import com.phegon.phegonbank.enums.TransactionStatus;
import com.phegon.phegonbank.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data // Generates getters, setters, toString, equals, and hashCode methods
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //exclude null fields when sending response to client
@JsonIgnoreProperties(ignoreUnknown = true) //ignore unknown fields when receiving request from client
public class TransactionDto {

    private Long id;

    private BigDecimal amount;

    private TransactionType transactionType;

    private LocalDateTime transactionDate = LocalDateTime.now();

    private String description;

    private TransactionStatus status;

    @JsonBackReference
    private AccountDto accountDto;

    //for transfer
    private String sourceAccount;
    private String destinationAccount;
}
