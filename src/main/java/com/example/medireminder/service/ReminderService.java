package com.example.medireminder.service;



import com.example.medireminder.dto.ReminderRequestDTO;
import com.example.medireminder.dto.ReminderResponseDTO;
import com.example.medireminder.model.Reminder;
import com.example.medireminder.repository.ReminderRepository;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReminderService {

    private final ReminderRepository reminderRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    // Це і є Constructor Injection! Жодних @Autowired
    public ReminderService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    public ReminderResponseDTO createReminder(ReminderRequestDTO dto) {
        LocalTime parsedTime = LocalTime.parse(dto.getTime(), formatter);
        Reminder reminder = new Reminder(dto.getMedicineName(), parsedTime, dto.getDosage());
        Reminder saved = reminderRepository.save(reminder);
        return mapToResponseDTO(saved);
    }

    public List<ReminderResponseDTO> getAllReminders() {
        return reminderRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private ReminderResponseDTO mapToResponseDTO(Reminder r) {
        return new ReminderResponseDTO(r.getId(), r.getMedicineName(), r.getTime().format(formatter), r.getDosage());
    }
}