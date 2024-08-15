package org.alouastudios.easytagalogbackend.word;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {

    List<Word> findByTagalogContaining(String tagalog);
}
