package com.example.medireminder.service;

import com.example.medireminder.dto.ReminderRequestDTO;
import com.example.medireminder.dto.ReminderResponseDTO;
import com.example.medireminder.model.Reminder;
import com.example.medireminder.repository.ReminderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ReminderServiceTest {

    private ReminderRepository reminderRepository;
    private ReminderService reminderService;

    @BeforeEach
    void setUp() {
        // Створюємо "фейковий" репозиторій за допомогою Mockito
        reminderRepository = Mockito.mock(ReminderRepository.class);
        reminderService = new ReminderService(reminderRepository);
    }

    @Test
    void testCreateReminder_Success() {
        // 1. Готуємо вхідні дані (DTO)
        ReminderRequestDTO request = new ReminderRequestDTO();
        request.setMedicineName("Ibuprofen");
        request.setTime("08:00");
        request.setDosage("1 tablet");

        // Готуємо те, що нібито поверне база даних
        Reminder savedReminder = new Reminder("Ibuprofen", LocalTime.of(8, 0), "1 tablet");
        Mockito.when(reminderRepository.save(any(Reminder.class))).thenReturn(savedReminder);

        // 2. Викликаємо метод, який тестуємо
        ReminderResponseDTO response = reminderService.createReminder(request);

        // 3. Перевіряємо результат
        assertNotNull(response);
        assertEquals("Ibuprofen", response.getMedicineName());
        assertEquals("08:00", response.getTime());
        assertEquals("1 tablet", response.getDosage());
    }

    @Test
    void testGetAllReminders() {
        // Готуємо фейковий список з бази
        Reminder r = new Reminder("Analgin", LocalTime.of(14, 30), "2 drops");
        Mockito.when(reminderRepository.findAll()).thenReturn(List.of(r));

        List<ReminderResponseDTO> result = reminderService.getAllReminders();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Analgin", result.get(0).getMedicineName());
    }
}