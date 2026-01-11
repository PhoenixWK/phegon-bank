package com.phegon.phegonbank.account.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.phegon.phegonbank.auth_users.dtos.UserDto;
import com.phegon.phegonbank.enums.AccountStatus;
import com.phegon.phegonbank.enums.AccountType;
import com.phegon.phegonbank.enums.Currency;
import com.phegon.phegonbank.transaction.dtos.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;

    private String accountNumber;

    private BigDecimal balance;

    private AccountType accountType;

    //It means this field will be ignored to prevent recursion, UserDTO will include account dto normally
    //@JsonManagedReference in UserDTO, @JsonBackReference in AccountDTO
    @JsonBackReference
    private UserDto user;

    private Currency currency;

    private AccountStatus accountStatus;

    @JsonManagedReference
    private List<TransactionDto> transactions = new ArrayList<>();
}
