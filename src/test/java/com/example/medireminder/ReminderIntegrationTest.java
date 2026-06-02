package com.example.medireminder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Вмикає профіль з H2 базою даних із нашого application.yml
class ReminderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateAndGetReminders_Integration() throws Exception {
        // 1. Відправляємо POST-запит на створення ліків
        String jsonRequest = """
                {
                    "medicineName": "Aspirin",
                    "time": "22:00",
                    "dosage": "1/2 tablet"
                }
                """;

        mockMvc.perform(post("/api/reminders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated()) // Очікуємо статус 201 Created
                .andExpect(jsonPath("$.message").value("Reminder created successfully"));

        // 2. Відразу перевіряємо через GET-запит, чи з'явилися ліки в базі
        mockMvc.perform(get("/api/reminders"))
                .andExpect(status().isOk()) // Очікуємо статус 200 OK
                .andExpect(jsonPath("$[0].medicineName").value("Aspirin"))
                .andExpect(jsonPath("$[0].time").value("22:00"));
    }
}