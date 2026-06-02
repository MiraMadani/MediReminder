package com.example.medireminder.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "reminders")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String medicineName;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private String dosage;

    public Reminder() {}
    public Reminder(String medicineName, LocalTime time, String dosage) {
        this.medicineName = medicineName;
        this.time = time;
        this.dosage = dosage;
    }
    public Long getId() { return id; }
    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String m) { this.medicineName = m; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime t) { this.time = t; }
    public String getDosage() { return dosage; }
    public void setDosage(String d) { this.dosage = d; }
}