package com.phegon.phegonbank.auth_users.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Table(name = "password_reset_code")
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    /*
        @OneToOne represents one-to-one relationship between entities
        targetEntity is used to explicitly specify the class of the related entity in a @OneToOne relationship
        It looks like you specify the girlfriend who handshaking with you (1-1)
     */
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    private LocalDateTime expiredAt;

    private boolean used;
}
