package com.phegon.phegonbank.auth_users.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder //is a Lombok annotation that automatically generates the Builder Pattern for your class, The Builder Pattern makes it easier and cleaner to create objects with multiple fields
public class LoginResponse {
    private String token;
    private List<String> roles;
}
