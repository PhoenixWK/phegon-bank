package com.phegon.phegonbank.auth_users.entity;

import com.phegon.phegonbank.account.entity.Account;
import com.phegon.phegonbank.role.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;
    private String phoneNumber;

    @Email
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email is required")
    private String email;

    private String password;
    private String profilePictureUrl;
    private boolean active = true;

    /*
        one user can have many roles, one role can be assigned to many users, it will create a join table
        @ManyToMany relationship is used to represent this relationship
        fetch = FetchType.EAGER is used to load roles along with user entity, it means when user is fetched from database, roles will be fetched as well

        @JoinTable is used to specify the join table name and join columns
        joinColumns = @JoinColumn(name = "user_id") means the user_id column in the join table will refer to the user entity
        inverseJoinColumns = @JoinColumn(name = "role_id") means the role_id column
    */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> role;

    /*
        @OneToMany relationship is used to represent the relationship between User and Account entities
        mappedBy = "user" means that the foreign key is in the Account entity, it refers to the user field in the Account entity
        cascade = CascadeType.ALL means that when a user is deleted, all associated accounts will be deleted as well
    */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}
