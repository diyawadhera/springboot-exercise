package com.diya.model;

import jakarta.persistence.Entity;

@Entity
public class Movie extends Video {
    // No-args constructor needed for Jackson deserialization
    public Movie() {
        super("", "");
    }

    // Constructor
    public Movie(String title, String genre) {
        super(title, genre);
    }

    // Implement abstract play() method
    @Override
    public void play() {
        System.out.println("Playing movie: " + this.getTitle());
    }

    // Overloaded play() method with quality parameter
    public void play(String quality) {
        System.out.println("Playing movie in " + quality + " quality");
    }
}
