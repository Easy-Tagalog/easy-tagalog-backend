package org.alouastudios.easytagalogbackend.word;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// @Data annotation includes getters, setters, tostring

@Data
@NoArgsConstructor
@Component
@Entity
@Table(name = "words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @Column(unique = true, nullable = false)
    private List<String> english = new ArrayList<String>();

    @Column(length = 30, nullable = false)
    private String tagalog;

    @Column(nullable = false)
    private String root;

    private String accents; // Comma-separated "1,3,5"

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

    public List<Integer> getAccents() {
        String[] accents = this.accents.split(",");

        List<Integer> result = new ArrayList<>();

        for (String accent: accents) {
            result.add(Integer.parseInt(accent));
        }

        return result;
    }
}
