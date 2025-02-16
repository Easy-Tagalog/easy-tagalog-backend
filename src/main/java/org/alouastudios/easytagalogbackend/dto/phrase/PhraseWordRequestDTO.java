package org.alouastudios.easytagalogbackend.dto.phrase;

import org.alouastudios.easytagalogbackend.enums.Aspect;
import org.alouastudios.easytagalogbackend.enums.PartOfSpeech;

import java.util.UUID;

public record PhraseWordRequestDTO(
        UUID wordUuid,  // Required if canSkip is null
        PartOfSpeech partOfSpeech, // Required if words translations.length > 1
        Boolean useLinkedWord,  // Not required
        Aspect aspect,  // Not required
        String aspectMeaning,   // Not required
        Boolean canSkip // Not required (for person/place names)
) {
}
