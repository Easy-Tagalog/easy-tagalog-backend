package org.alouastudios.easytagalogbackend.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/words")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping("/")
    public List<Word> getAllWords() {
        return wordService.getAllWords();
    };

    @GetMapping("/{id}")
    public Word getWordById(@PathVariable int id) {
        return wordService.getWordById(id);
    };

    @GetMapping("/search/{searchQuery}")
    public List<Word> searchWord(@PathVariable String searchQuery) {
        return wordService.getWordsBySearchQuery(searchQuery);
    }

    @PostMapping("/")
    public Word addWord(Word word) {
        return wordService.addWord(word);
    };

    @PutMapping("/{id}")
    public Word updateWord(Word word) {
        return wordService.updateWord(word);
    };

    @DeleteMapping("/{id}")
    public String deleteWord(@PathVariable int id) {
        wordService.deleteWord(id);
        return "Deleted Job Id: " + id;
    };

    @GetMapping("/load")
    public String loadWords() {
        wordService.load();

        return "Successfully Loaded Words";
    }
}
