package com.travel.UserService.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"mobileNo"})
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(unique = true)
    private String firstName;

    @Size(max = 50, message = "Last name can have a maximum of 50 characters")
    private String lastName;

    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid mobile number format")
    @NotBlank(message = "Mobile number cannot be blank")
    @Column(unique = true)
    private String mobileNo;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    @Column(unique = true)
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    private String password;

    @NotNull(message = "Role cannot be null")
    private ROLE role;

    private LocalDateTime createdAt;
}
