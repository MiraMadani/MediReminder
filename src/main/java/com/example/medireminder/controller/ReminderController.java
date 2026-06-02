package com.example.medireminder.controller;


import com.example.medireminder.dto.ReminderRequestDTO;
import com.example.medireminder.dto.ReminderResponseDTO;
import com.example.medireminder.service.ReminderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reminders")
@Tag(name = "Reminders API")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping
    @Operation(summary = "Створити нагадування")
    public ResponseEntity<Map<String, Object>> createReminder(@Valid @RequestBody ReminderRequestDTO dto) {
        ReminderResponseDTO created = reminderService.createReminder(dto);
        Map<String, Object> response = new HashMap<>();
        response.put("id", created.getId());
        response.put("message", "Reminder created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Отримати список нагадувань")
    public ResponseEntity<List<ReminderResponseDTO>> getAllReminders() {
        return ResponseEntity.ok(reminderService.getAllReminders());
    }
}