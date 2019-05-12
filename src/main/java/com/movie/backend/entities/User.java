package com.movie.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    @Size(min = 8, max = 40, message = "Email length must be between 8 and 40 character")
    @Column(name = "email", unique = true, insertable = true, updatable = true)
    private String email;

    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 2, max = 600, message = "Password length must be between 2 and 600 character")
    @Column(name = "password", insertable = true, updatable = true)
    private String password;

    @NotNull(message = "Username cannot be null")
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 2, max = 60, message = "Username length must be between 2 and 60 character")
    @Column(name = "username", unique = true, insertable = true, updatable = true)
    private String username;

    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name cannot be empty")
    @Size(min = 2, max = 60, message = "First name length must be between 2 and 60 character")
    @Column(name = "first_name", insertable = true, updatable = true)
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @NotEmpty(message = "Last name cannot be empty")
    @Size(min = 2, max = 60, message = "Last name length must be between 2 and 60 character")
    @Column(name = "last_name", insertable = true, updatable = true)
    private String lastName;

    @NotNull(message = "Image cannot be null")
    @NotEmpty(message = "Image cannot be empty")
    @Size(min = 2, max = 6000, message = "Image length must be between 2 and 6000 character")
    @Column(name = "image", insertable = true, updatable = true)
    private String image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles")
    private List<Role> roles;

    // Convenient method to add new role
    public void addRole(Role role) {
        if( this.roles == null ) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(role);
    }

}
