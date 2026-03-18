package com.diya.controller;

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
import com.diya.service.VideoService;
@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/all")
    public List<Video> getAllVideos(){
        return videoService.getAllVideos();
    }

    @GetMapping("/available")
    public List<Video> getAvailableVideos(){
        return videoService.getAvailableVideos();
    }

    @PostMapping("/add/movie")
    public String addMovie(@RequestBody Movie movie){
        videoService.addMovie(movie);
        return "added movie: " + movie.getTitle();
    }

    @PutMapping("/{title}/rent")
    public String rentVideo(@PathVariable String title){
        boolean rented = videoService.rentVideo(title);
        return rented ? "rented video: " + title : "Video not found: " + title;
    }

    @PutMapping("/{title}/return")
    public String returnVideo(@PathVariable String title){
        boolean returned = videoService.returnVideo(title);
        return returned ? "returned video: " + title : "Video not found: " + title;
    }
}
