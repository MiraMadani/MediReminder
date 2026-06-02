package com.example.medireminder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;

public class ReminderRequestDTO {
    @NotBlank(message = "Назва ліків не може бути порожньою")
    @Schema(example = "Ibuprofen")
    private String medicineName;

    @NotBlank(message = "Час обов'язковий")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "Формат часу повинен бути HH:mm")
    @Schema(example = "08:00")
    private String time;

    @NotBlank(message = "Дозування обов'язкове")
    @Schema(example = "1 tablet")
    private String dosage;

    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String m) { this.medicineName = m; }
    public String getTime() { return time; }
    public void setTime(String t) { this.time = t; }
    public String getDosage() { return dosage; }
    public void setDosage(String d) { this.dosage = d; }
}