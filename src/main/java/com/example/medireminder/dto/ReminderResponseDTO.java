package com.example.medireminder.dto;



public class ReminderResponseDTO {
    private Long id;
    private String medicineName;
    private String time;
    private String dosage;

    public ReminderResponseDTO(Long id, String medicineName, String time, String dosage) {
        this.id = id;
        this.medicineName = medicineName;
        this.time = time;
        this.dosage = dosage;
    }
    public Long getId() { return id; }
    public String getMedicineName() { return medicineName; }
    public String getTime() { return time; }
    public String getDosage() { return dosage; }
}