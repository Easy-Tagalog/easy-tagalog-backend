package org.alouastudios.easytagalogbackend.lesson.question;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.alouastudios.easytagalogbackend.phrase.Phrase;
import org.alouastudios.easytagalogbackend.word.Word;

import java.util.List;

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String question;
    private String answer;
    private String questionType;

    private List<Word> wordOptions;
    private List<Phrase> phraseOptions;

}
