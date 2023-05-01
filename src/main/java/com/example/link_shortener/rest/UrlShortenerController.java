package com.example.link_shortener.rest;

import static org.springframework.http.HttpStatus.FOUND;

import com.example.link_shortener.dto.UrlShortRequest;
import com.example.link_shortener.service.UrlService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlShortenerController {

  private final UrlService urlService;

  @Value("${shortened.service.base.url}")
  private String shortenedBaseUrl;

  @PostMapping("/short")
  public ResponseEntity<Void> getShortenedUrl(@RequestBody UrlShortRequest request) {
    String shortedUrlId = urlService.shortUrl(request);
    URI uri = URI.create(shortenedBaseUrl + shortedUrlId);
    return ResponseEntity.created(uri).build();
  }

  @GetMapping("/short/{shortenUrlId}")
  public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortenUrlId) {
    String originalUrl = urlService.findOriginalUrl(shortenUrlId);
    return ResponseEntity
        .status(FOUND)
        .location(URI.create(originalUrl))
        .build();
  }
}
