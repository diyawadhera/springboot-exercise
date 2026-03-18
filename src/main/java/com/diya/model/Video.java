package com.diya.model;


public abstract class Video {
    private String title;
    private String genre;
    private boolean available;

    // Constructor
    public Video(String title, String genre) {
        this.title = title;
        this.genre = genre;
        this.available = true;
    }

    // Abstract method
    public abstract void play();

    // Getter and Setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for genre
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Getter and Setter for available
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Rent video method
    public void rentVideo() {
        this.available = false;
        System.out.println("Renting " + this.title + "...");
    }

    // Return video method
    public void returnVideo() {
        this.available = true;
        System.out.println("Returning " + this.title + "...");
    }
}
