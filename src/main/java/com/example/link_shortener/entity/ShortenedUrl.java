package com.example.link_shortener.entity;

import static java.util.Objects.nonNull;

import com.example.link_shortener.dto.UrlShortRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shortened_urls", schema = "bobocode", catalog = "bobocode")
public class ShortenedUrl {

  @Id
  String id;

  @Column(name = "original_url")
  String originalUrl;

  @Column(name = "title")
  String title;

  @Column(name = "created_at")
  LocalDateTime createdAt;

  public ShortenedUrl populate(UrlShortRequest request) {
    this.id = UUID.randomUUID().toString();
    this.originalUrl = request.getUrl();
    this.title = request.getTitle();
    this.createdAt = LocalDateTime.now();
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShortenedUrl that = (ShortenedUrl) o;
    return nonNull(id) && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
