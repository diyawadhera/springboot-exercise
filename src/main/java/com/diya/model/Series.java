package com.diya.model;

import jakarta.persistence.Entity;

@Entity
public class Series extends Video {
    // No-args constructor needed for Jackson deserialization
    public Series() {
        super("", "");
    }

    // Constructor
    public Series(String title, String genre) {
        super(title, genre);
    }

    // Implement abstract play() method
    @Override
    public void play() {
        System.out.println("Playing episode of series: " + this.getTitle());
    }
}
