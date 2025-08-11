package com.happyhost.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings")  // ✅ CORRECT
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    private String name;
    private String email;
    private String time;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Booking() {
    }

    public Booking(Long id, String name, String email, String time, Status status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.time = time;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

