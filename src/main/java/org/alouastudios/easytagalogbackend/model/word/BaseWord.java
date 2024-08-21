package org.alouastudios.easytagalogbackend.model.word;

import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
public class BaseWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String tagalog;

    @Column(nullable = false)
    private String root;

    private String accents; // Comma-separated "1,3,5"

    @Column(unique = true)
    private String audioUrl;
}
