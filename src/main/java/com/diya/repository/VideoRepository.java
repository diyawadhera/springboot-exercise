package com.diya.repository;

import com.diya.model.Video;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository // Indicates that this interface is a Spring Data repository.
public interface VideoRepository extends JpaRepository<Video, Long> {
    // This has default CRUD operations covered

    List<Video> findByAvailableTrue();

    Optional<Video> findByTitle(String title);
}
