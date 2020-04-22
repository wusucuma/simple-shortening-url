package com.bera.musinsa.controller;

import com.bera.musinsa.dto.ShortUrlDto;
import com.bera.musinsa.dto.ShortUrlRequestDto;
import com.bera.musinsa.service.ShortService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ShortController {

  private final ShortService shortService;

  @PostMapping("")
  public ResponseEntity<ShortUrlDto> shortUrl(
      @RequestBody ShortUrlRequestDto.CreateShortUrl createShortUrl) {
    ShortUrlDto shortUrl = shortService.getShortUrl(createShortUrl.getOriginUrl());
    return ResponseEntity.ok(shortUrl);
  }
}
