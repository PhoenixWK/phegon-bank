package com.phegon.phegonbank.auth_users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationRequest {

    @NotBlank(message = "Firstname is required")
    private String firstName;

    private String lastName;

    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email //a validation annotation in Spring (specifically from Jakarta Bean Validation / Hibernate Validator) used to validate that a String field contains a valid email format.
    private String email;

    private List<String> roles;

    @NotBlank(message = "Password is required")
    private String password;
}
