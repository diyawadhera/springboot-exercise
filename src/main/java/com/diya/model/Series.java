package com.diya.model;

public class Series extends Video {
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
