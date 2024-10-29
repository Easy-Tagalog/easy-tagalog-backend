package org.alouastudios.easytagalogbackend.controller;

import org.alouastudios.easytagalogbackend.dto.LessonRequestDTO;
import org.alouastudios.easytagalogbackend.dto.response.lessonResponse.LessonResponseDTO;
import org.alouastudios.easytagalogbackend.service.LessonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public List<LessonResponseDTO> getAllLessons() { return lessonService.getAllLessons(); }

    @GetMapping("/{uuid}")
    public LessonResponseDTO getLessonById(@PathVariable UUID uuid) { return lessonService.getLessonByUUID(uuid); }

    @PostMapping
    public LessonResponseDTO addLesson(@RequestBody LessonRequestDTO lessonRequestDTO) {
        return lessonService.addLesson(lessonRequestDTO);
    }
}
