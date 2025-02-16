package org.alouastudios.easytagalogbackend.model.phrases;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.alouastudios.easytagalogbackend.model.words.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "phrases")
@ToString(exclude = "words")
@EqualsAndHashCode(exclude = "words")
public class Phrase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    @Column(nullable = false, unique = true)
    private String tagalog;

    @Column(nullable = false)
    private String english;

    @Column(nullable = false)
    private Boolean isQuestion = false;

    @Column(nullable = true)
    private String note;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "phrase_words",
            joinColumns = @JoinColumn(name = "phrase_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id")
    )
    private Set<Word> words;

    @ElementCollection
    @CollectionTable(name = "phrase_word_meanings", joinColumns = @JoinColumn(name = "phrase_id"))
    @Column(name = "meaning")
    private List<String> phraseWordMeanings = new ArrayList<>();

    @PrePersist
    public void generateUUID() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
