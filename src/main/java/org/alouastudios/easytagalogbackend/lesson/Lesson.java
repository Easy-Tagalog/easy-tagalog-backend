package org.alouastudios.easytagalogbackend.lesson;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.alouastudios.easytagalogbackend.lesson.question.Question;

import java.util.List;

public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;

    private List<Question> questions;

}
