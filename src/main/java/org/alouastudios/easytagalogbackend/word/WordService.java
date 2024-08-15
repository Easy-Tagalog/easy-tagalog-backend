package org.alouastudios.easytagalogbackend.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public List<Word> getWordsBySearchQuery(String searchQuery) {
        return wordRepository.findByTagalogContaining(searchQuery);
    }

    public Word getWordById(int id) {
        return wordRepository.findById(id).orElse(null);
    }

    public Word addWord(Word word) {
        return wordRepository.save(word);
    }

    public Word updateWord(Word word) {
        return wordRepository.save(word);
    }

    public void deleteWord(int id) {
        wordRepository.deleteById(id);
    }

    public void load() {
        List<Word> words = new ArrayList<>();

        words.add(new Word(1, List.of("eat"), "kumain", "kain", "http://example.com/audio/eat.mp3", "verb", false));
        words.add(new Word(2, List.of("day", "sun"), "araw", "araw", "http://example.com/audio/araw.mp3", "noun", null));

        wordRepository.saveAll(words);
    }

}
