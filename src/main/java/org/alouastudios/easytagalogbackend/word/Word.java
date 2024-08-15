package org.alouastudios.easytagalogbackend.word;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

// @Data annotation includes getters, setters, tostring

@Data
@NoArgsConstructor
@Component
@Entity
@Table(name = "words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private List<String> english;

    @Column(nullable = false)
    private String tagalog;

    @Column(nullable = false)
    private String root;

    @Column(nullable = false)
    private List<int> accents;

    @Column(unique = true)
    private String audioUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PartOfSpeech partOfSpeech;

    private Boolean isIrregularVerb;

    @Transient
    public boolean isVerb() {
        return partOfSpeech == PartOfSpeech.VERB;
    }
}
