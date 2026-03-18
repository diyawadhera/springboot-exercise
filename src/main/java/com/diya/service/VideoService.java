package com.diya.service;

import java.util.List;

import com.diya.model.Movie;
import com.diya.model.Video;

/**
 * Service interface defining operations for managing videos.
 */
public interface VideoService {

    List<Video> getAllVideos();

    List<Video> getAvailableVideos();

    /**
     * Adds a movie to the collection.
     *
     * @param movie the movie to add
     */
    void addMovie(Movie movie);

    /**
     * Marks the video with the given title as rented.
     *
     * @param title the title of the video to rent
     * @return true if the video was found and rented; false otherwise
     */
    boolean rentVideo(String title);

    /**
     * Marks the video with the given title as returned.
     *
     * @param title the title of the video to return
     * @return true if the video was found and returned; false otherwise
     */
    boolean returnVideo(String title);
}
