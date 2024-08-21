package org.alouastudios.easytagalogbackend.model.word;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.alouastudios.easytagalogbackend.enums.PartOfSpeech;

import java.util.Objects;
import java.util.Set;

// @Data annotation includes getters, setters, tostring, equals, and hashcode

@Data
@NoArgsConstructor
@Entity
@Table(
        name = "words",
        uniqueConstraints = {@UniqueConstraint(name = "UniqueWordAndAccents", columnNames = {"tagalog", "accents"})}
)
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String tagalog;

    @Column(nullable = false)
    private String root;

    private String accents; // Comma-separated, ex: "1,3,5"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PartOfSpeech partOfSpeech;

    private String alternateSpelling; // ex: siya == sya

    private Boolean isIrregularVerb;

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Conjugation> conjugations;

    @Column(unique = true)
    private String audioUrl;

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", tagalog='" + tagalog + '\'' +
                ", root='" + root + '\'' +
                ", accents='" + accents + '\'' +
                ", partOfSpeech=" + partOfSpeech +
                ", alternateSpelling='" + alternateSpelling + '\'' +
                ", isIrregularVerb=" + isIrregularVerb +
                ", audioUrl='" + audioUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(id, word.id) && Objects.equals(tagalog, word.tagalog) && Objects.equals(root, word.root) && Objects.equals(accents, word.accents) && partOfSpeech == word.partOfSpeech && Objects.equals(alternateSpelling, word.alternateSpelling) && Objects.equals(isIrregularVerb, word.isIrregularVerb) && Objects.equals(audioUrl, word.audioUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagalog, root, accents, partOfSpeech, alternateSpelling, isIrregularVerb, audioUrl);
    }
}
