package com.diya;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.diya.model.Movie;
import com.diya.repository.VideoRepository;
import com.diya.service.VideoService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner test(VideoRepository videoRepository, VideoService videoService) {
		return args -> {
			videoService.addMovie(new Movie("Inception", "Sci-Fi"));
			videoService.addMovie(new Movie("The Matrix", "Sci-Fi"));

			System.out.println("All videos:");
			videoService.getAllVideos().forEach(System.out::println);

			System.out.println("\nRenting 'Inception'...");
			videoService.rentVideo("Inception");

			System.out.println("\nAvailable videos:");
			videoService.getAvailableVideos().forEach(System.out::println);
		};
		// AnnotationConfigApplicationContext context
		// 	= new AnnotationConfigApplicationContext();
		// context.scan("com.diya");

		// context.refresh();

		// VideoService myServiceClass
		// 	= context.getBean(VideoService.class);

		// Movie mymovie = new Movie("Inception", "Sci-Fi");

		// // Testing the addMovie method
		// myServiceClass.addMovie(mymovie);
		// System.out.println("Movie added: " + mymovie.getTitle());

		// myServiceClass.getAllVideos().forEach(video -> System.out.println("Video in collection: " + video.getTitle()));
		// // Closing the spring context
		// // using close() method
		// context.close();

	}

}
