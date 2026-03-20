package com.diya;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureWebMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.diya.controller.VideoController;
import com.diya.model.Movie;
import com.diya.service.VideoService;

@WebMvcTest(VideoController.class)
@AutoConfigureWebMvc
public class VideoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VideoService videoService;

    @Test
    public void testGetAllVideos() throws Exception {
        // GET /api/videos/all
        when(videoService.getAllVideos()).thenReturn(List.of(new Movie("Inception", "Sci-Fi")));

        // Videos present
        mockMvc.perform(get("/api/videos/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Inception"));

        // No videos present
        when(videoService.getAllVideos()).thenReturn(List.of());

        mockMvc.perform(get("/api/videos/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void testGetAvailableVideos() throws Exception {
        // GET /api/videos/available
        when(videoService.getAvailableVideos()).thenReturn(List.of(new Movie("Inception", "Sci-Fi")));

        // Available videos present
        mockMvc.perform(get("/api/videos/available"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].available").value(true));

    }

    @Test
    public void testAddMovie() throws Exception {
        // GET /api/videos/add/movie
        doNothing().when(videoService).addMovie(any(Movie.class));

        mockMvc.perform(post("/api/videos/add/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                          {"title":"Baby's Day Out","genre":"Comedy"}
                        """))
                .andExpect(status().isOk())
                .andExpect(content().string("added movie: Baby's Day Out"));

    }

    @Test
    public void testRentVideo() throws Exception {
        // PUT /api/videos/{title}/rent

        // Successful rent
        when(videoService.rentVideo("Baby's Day Out")).thenReturn(true);

        mockMvc.perform(put("/api/videos/{title}/rent", "Baby's Day Out"))
                .andExpect(status().isOk())
                .andExpect(content().string("rented video: Baby's Day Out"));

        // Failed rent (e.g., already rented)
        when(videoService.rentVideo("Baby's Day Out")).thenReturn(false);

        mockMvc.perform(put("/api/videos/{title}/rent", "Baby's Day Out"))
                .andExpect(status().isOk())
                .andExpect(content().string("Some issue occurred while renting: Baby's Day Out"));

    }

    @Test
    public void testReturnVideo() throws Exception {
        // PUT /api/videos/{title}/return

        // Successful return
        when(videoService.returnVideo("Baby's Day Out")).thenReturn(true);

        mockMvc.perform(put("/api/videos/{title}/return", "Baby's Day Out"))
                .andExpect(status().isOk())
                .andExpect(content().string("returned video: Baby's Day Out"));

        // Failed return (e.g., video not found)
        when(videoService.returnVideo("Baby's Day Out")).thenReturn(false);

        mockMvc.perform(put("/api/videos/{title}/return", "Baby's Day Out"))
                .andExpect(status().isOk())
                .andExpect(content().string("Some issue occurred while returning: Baby's Day Out"));
    }

}
