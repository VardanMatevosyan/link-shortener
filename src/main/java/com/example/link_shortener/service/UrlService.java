package com.example.link_shortener.service;

import com.example.link_shortener.dto.UrlShortRequest;
import java.net.URI;

public interface UrlService {

  String shortUrl(UrlShortRequest request);
  String findOriginalUrl(String shortenUrlId);

}
