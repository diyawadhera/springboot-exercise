package com.diya.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diya.model.Movie;
import com.diya.model.Video;
@RestController
@RequestMapping("/api/videos")
public class VideoController {
    private List<Video> videos = new ArrayList<>();

    @GetMapping("/all")
    public List<Video> getAllVideos(){
        return videos;
    }

    @GetMapping("/available")
    public List<Video> getAvailableVideos(){
        return videos.stream().filter(Video::isAvailable).toList();
    }

    @PostMapping("/add/movie")
    public String addMovie(@RequestBody Movie movie){
        videos.add(movie);
        return "added movie: " + movie.getTitle();
    }

    @PutMapping("/{title}/rent")
    public String rentVideo(@PathVariable String title){
        for (Video video : videos) {
            if (video.getTitle().equals(title)) {
                video.rentVideo();
                return "rented video: " + title;
            }
        }
        return "Video not found: " + title;
    }

    @PutMapping("/{title}/return")
    public String returnVideo(@PathVariable String title){
        for (Video video : videos) {
            if (video.getTitle().equals(title)) {
                video.returnVideo();
                return "returned video: " + title;
            }
        }
        return "Video not found: " + title;
    }
}
