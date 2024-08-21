package org.alouastudios.easytagalogbackend.service;

import lombok.RequiredArgsConstructor;
import org.alouastudios.easytagalogbackend.enums.PartOfSpeech;
import org.alouastudios.easytagalogbackend.enums.Tense;
import org.alouastudios.easytagalogbackend.model.word.Conjugation;
import org.alouastudios.easytagalogbackend.model.word.Word;
import org.alouastudios.easytagalogbackend.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public List<Word> getWordsBySearchQuery(String searchQuery) {
        return wordRepository.findByTagalogContaining(searchQuery);
    }

    public Word getWordById(Long id) {
        return wordRepository.findById(id).orElse(null);
    }

    public Word addWord(Word word) {

        // Checks if word is of partOfSpeech type VERB
        if (word.getPartOfSpeech() == PartOfSpeech.VERB) {

            // Must provide isIrregularVerb field
            if (word.getIsIrregularVerb() == null) {
                throw new RuntimeException("Verb must have isIrregularVerb");
            }

            // For verbs, check user gave conjugations field
            if (word.getConjugations() == null || word.getConjugations().isEmpty()) {
                throw new RuntimeException("Verbs must have conjugations");
            }

            // Check user gave 3 conjugations
            if (word.getConjugations().size() != 3) {
                throw new RuntimeException("Verbs must have only 3 conjugations");
            }

            // Check user provided PAST, PRESENT, FUTURE tenses
            boolean past = false, present = false, future = false;
            for (Conjugation c : word.getConjugations()) {
                if (c.getTense() == Tense.PAST) past = true;
                if (c.getTense() == Tense.PRESENT) present = true;
                if (c.getTense() == Tense.FUTURE) future = true;
            }

            if (!past) throw new RuntimeException("Verb missing past conjugation");
            if (!present) throw new RuntimeException("Verb missing present conjugation");
            if (!future) throw new RuntimeException("Verb missing future conjugation");

            // If passed all checks, set conjugation to word
            for (Conjugation c : word.getConjugations()) {
                c.setWord(word);
            }
        }

        return wordRepository.save(word);
    }

    public Word updateWord(Word word) {
        return wordRepository.save(word);
    }

    public void deleteWord(Long id) {
        wordRepository.deleteById(id);
    }

    public void load() {
//        Word word = new Word();
//        word.setTagalog("test");
//        word.setPartOfSpeech(PartOfSpeech.NOUN);
//        word.setAccents("1");
//        word.setAudioUrl("test.mp3");
//        word.setRoot("tes");
//
//        List<Conjugation> conjugations = new ArrayList<Conjugation>();
//        Conjugation pastConjugation = new Conjugation();
//        pastConjugation.setEnglish("test");
//        pastConjugation.setTense(Tense.PAST);
//        pastConjugation.setRoot("test");
//        pastConjugation.setAccents("123");
//        pastConjugation.setAudioUrl("test.mp3");
//        pastConjugation.setTagalog("ast");
//        conjugations.add(pastConjugation);
//
//        word.setConjugations(conjugations);
//
//        wordRepository.save(word);
    }

}
