package com.diya.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diya.model.Movie;
import com.diya.model.Video;

/**
 * Concrete service implementation that manages an in-memory video collection.
 */
@Service
public class VideoServiceImpl implements VideoService {

    private final List<Video> videos = new ArrayList<>();

    @Override
    public List<Video> getAllVideos() {
        return List.copyOf(videos);
    }

    @Override
    public List<Video> getAvailableVideos() {
        return videos.stream().filter(Video::isAvailable).toList();
    }

    @Override
    public void addMovie(Movie movie) {
        videos.add(movie);
    }

    @Override
    public boolean rentVideo(String title) {
        Optional<Video> optional = findByTitle(title);
        if (optional.isPresent()) {
            optional.get().rentVideo();
            return true;
        }
        return false;
    }

    @Override
    public boolean returnVideo(String title) {
        Optional<Video> optional = findByTitle(title);
        if (optional.isPresent()) {
            optional.get().returnVideo();
            return true;
        }
        return false;
    }

    private Optional<Video> findByTitle(String title) {
        return videos.stream().filter(v -> v.getTitle().equals(title)).findFirst();
    }
}
