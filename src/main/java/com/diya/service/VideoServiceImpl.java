package com.diya.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diya.model.Movie;
import com.diya.model.Video;
import com.diya.repository.VideoRepository;

/**
 * Concrete service implementation that manages an in-memory video collection.
 */
@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository; // This will be used for database operations

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll(); // Fetch all videos from the database
    }

    @Override
    public List<Video> getAvailableVideos() {
        return videoRepository.findByAvailableTrue(); // Fetch only available videos from the database
    }

    @Override
    public void addMovie(Movie movie) {
        videoRepository.save(movie); // Save the new movie to the database
    }

    @Override
    public boolean rentVideo(String title) {
        Optional<Video> optional = videoRepository.findByTitle(title);

        if (optional.isPresent()) {
            Video video = optional.get();

            if (!video.isAvailable()) {
                return false; // already rented
            }

            video.rentVideo(); // domain logic (unchanged)
            videoRepository.save(video); // ✅ persist change
            return true;
        }

        return false;
    }

    @Override
    public boolean returnVideo(String title) {
        Optional<Video> optional = videoRepository.findByTitle(title);

        if (optional.isPresent()) {
            Video video = optional.get();

            video.returnVideo(); // domain logic (unchanged)
            videoRepository.save(video); // ✅ persist change
            return true;
        }

        return false;
    }

    private Optional<Video> findByTitle(String title) {
        return videoRepository.findByTitle(title);
    }
}
