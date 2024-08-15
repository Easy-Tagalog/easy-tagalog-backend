package org.alouastudios.easytagalogbackend.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Component
@Entity
@Table(name = "users")
public class User {

    // TODO: Create unique and non null fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 35, unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdDate = Instant.now();

    @LastModifiedDate
    private Instant lastModifiedDate = Instant.now();
}
