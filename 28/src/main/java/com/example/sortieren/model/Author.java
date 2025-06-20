package com.example.sortieren.model;

import java.time.LocalDate;

public class Author {
    private String name;
    private LocalDate birthDate;
    private String nationality;

    // Default constructor for Instancio
    public Author() {
    }

    public Author(String name, LocalDate birthDate, String nationality) {
        this.name = name;
        this.birthDate = birthDate;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return name; // Simple representation for cleaner Book toString()
    }
} 