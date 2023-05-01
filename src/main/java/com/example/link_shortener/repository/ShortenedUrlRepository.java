package com.example.link_shortener.repository;

import com.example.link_shortener.entity.ShortenedUrl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, String> {

  @Query("select su.originalUrl from ShortenedUrl su where su.id = :id")
  Optional<String> findOriginalUrlWithId(String id);

}
