package org.alouastudios.easytagalogbackend.model.word;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class English {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String meaning;

//    @ManyToMany(mappedBy = "english")
//    @JsonIgnore
//    private List<Word> words;
}
