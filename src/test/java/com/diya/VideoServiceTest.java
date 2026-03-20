package com.diya;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.diya.model.Movie;
import com.diya.model.Video;
import com.diya.repository.VideoRepository;
import com.diya.service.VideoService;
import com.diya.service.VideoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private VideoServiceImpl videoService;

    @Test
    public void testGetAllVideos() throws Exception {
        List<Video> videos = List.of(
                new Movie("Inception", "Sci-Fi"),
                new Movie("Interstellar", "Sci-Fi"));

        when(videoRepository.findAll()).thenReturn(videos);

        List<Video> result = videoService.getAllVideos();

        assertEquals(2, result.size());
        assertEquals("Inception", result.get(0).getTitle());

        verify(videoRepository).findAll();

    }

    @Test
    public void testGetAvailableVideos() throws Exception {
        List<Video> availableVideos = List.of(
                new Movie("Inception", "Sci-Fi"),
                new Movie("Interstellar", "Sci-Fi"));

        when(videoRepository.findByAvailableTrue()).thenReturn(availableVideos);

        List<Video> result = videoService.getAvailableVideos();

        assertEquals(2, result.size());
        assertEquals("Inception", result.get(0).getTitle());

        verify(videoRepository).findByAvailableTrue();
    }

    @Test
    public void testAddMovie() throws Exception {

        Movie movie = new Movie("Inception", "Sci-Fi");

        videoService.addMovie(movie);

        verify(videoRepository).save(movie);

    }

    @Test
    public void testRentVideo_Unknown() throws Exception {
        when(videoRepository.findByTitle("Unknown")).thenReturn(Optional.empty());
        boolean result = videoService.rentVideo("Unknown");

        assertFalse(result);
        verify(videoRepository, never()).save(any());
    }

    @Test
    public void testRentVideo_Known_Rented() throws Exception {
        Movie movie = new Movie("Inception", "Sci-Fi");
        movie.rentVideo(); // already rented

        when(videoRepository.findByTitle(movie.getTitle())).thenReturn(Optional.of(movie));
        boolean result = videoService.rentVideo(movie.getTitle());

        assertFalse(result);
        verify(videoRepository, never()).save(any());
    }

    @Test
    public void testRentVideo_Known_Available() throws Exception {
        Movie movie = new Movie("Inception", "Sci-Fi");

        when(videoRepository.findByTitle(movie.getTitle())).thenReturn(Optional.of(movie));
        boolean result = videoService.rentVideo(movie.getTitle());

        assertTrue(result);
        verify(videoRepository).save(movie);
    }

    @Test
    public void testReturnVideo_Unknown() throws Exception {
        when(videoRepository.findByTitle("Unknown")).thenReturn(Optional.empty());
        boolean result = videoService.returnVideo("Unknown");
        assertFalse(result);
        verify(videoRepository, never()).save(any());
    }

    @Test
    public void testReturnVideo_Known() throws Exception {
        Movie movie = new Movie("Inception", "Sci-Fi");
        when(videoRepository.findByTitle(movie.getTitle())).thenReturn(Optional.of(movie));

        boolean result = videoService.returnVideo(movie.getTitle());
        assertTrue(result);
        verify(videoRepository).save(movie);
    }

}
