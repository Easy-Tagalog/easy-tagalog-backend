package org.alouastudios.easytagalogbackend.dto.phrase;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record PhraseRequestDTO(
        String tagalog,
        String english,
        Boolean isQuestion,
        Set<UUID> wordUuids,
        List<PhraseWordRequestDTO> phraseWordRequestDTOS
) {
}
