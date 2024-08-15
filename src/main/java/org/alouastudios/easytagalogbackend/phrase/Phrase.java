package org.alouastudios.easytagalogbackend.phrase;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.alouastudios.easytagalogbackend.word.Word;

import java.util.List;

public class Phrase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String english;
    private String tagalog;

    private List<Word> words;
}
