package org.alouastudios.easytagalogbackend.dto.word;

import org.alouastudios.easytagalogbackend.enums.Aspect;

import java.util.List;

public record ConjugationResponseDTO(
        String tagalog,
        String root,
        List<Integer> accents,
        String audioUrl,
        Aspect tense
) {
}
