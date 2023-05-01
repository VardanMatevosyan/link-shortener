package com.example.link_shortener.service.impl;

import static java.lang.String.format;

import com.example.link_shortener.dto.UrlShortRequest;
import com.example.link_shortener.entity.ShortenedUrl;
import com.example.link_shortener.exception.NotFoundUrlException;
import com.example.link_shortener.repository.ShortenedUrlRepository;
import com.example.link_shortener.service.UrlService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UrlServiceImpl implements UrlService {

  ShortenedUrlRepository shortenedUrlRepository;

  @Override
  @Transactional
  public String shortUrl(UrlShortRequest request) {
    ShortenedUrl shortenedUrl = new ShortenedUrl().populate(request);
    ShortenedUrl savedShortenedUrl = shortenedUrlRepository.save(shortenedUrl);
    return savedShortenedUrl.getId();
  }

  @Override
  @Transactional(readOnly = true)
  @Cacheable("original_urls")
  public String findOriginalUrl(String shortenUrlId) {
    return shortenedUrlRepository.findOriginalUrlWithId(shortenUrlId)
        .orElseThrow(() ->
            new NotFoundUrlException(format("Can't find original url by id %s", shortenUrlId)));
  }

}
