package com.diya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.diya.model.Movie;
import com.diya.service.VideoService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		AnnotationConfigApplicationContext context
			= new AnnotationConfigApplicationContext();
		context.scan("com.diya");

		context.refresh();

		VideoService myServiceClass
			= context.getBean(VideoService.class);

		Movie mymovie = new Movie("Inception", "Sci-Fi");

		// Testing the addMovie method
		myServiceClass.addMovie(mymovie);
		System.out.println("Movie added: " + mymovie.getTitle());

		myServiceClass.getAllVideos().forEach(video -> System.out.println("Video in collection: " + video.getTitle()));
		// Closing the spring context
		// using close() method
		context.close();
	}

}
