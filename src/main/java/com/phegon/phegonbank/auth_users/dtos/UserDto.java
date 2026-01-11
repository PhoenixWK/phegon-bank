package com.phegon.phegonbank.auth_users.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.phegon.phegonbank.account.dtos.AccountDto;
import com.phegon.phegonbank.role.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data // Generates getters, setters, toString, equals, and hashCode methods
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //exclude null fields when sending response to client
@JsonIgnoreProperties(ignoreUnknown = true) //ignore unknown fields when receiving request from client
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    @JsonIgnore //to avoid sending password in response to client
    private String password;

    private String profilePictureUrl;
    private boolean active = true;

    private List<Role> role;

    @JsonManagedReference // avoid return user -> account -> user, it is infinite loop, just return account
    private List<AccountDto> accounts;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
