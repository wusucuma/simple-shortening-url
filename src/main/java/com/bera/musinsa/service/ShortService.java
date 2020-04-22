package com.bera.musinsa.service;

import com.bera.musinsa.domain.OriginUrl;
import com.bera.musinsa.domain.ShortUrl;
import com.bera.musinsa.dto.ShortUrlDto;
import com.bera.musinsa.repository.OriginUrlRepository;
import com.bera.musinsa.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShortService {

  private final ShortUrlRepository shortUrlRepository;
  private final OriginUrlRepository originUrlRepository;

  @Value("${server.error.path}")
  private String errorPath;

  @Value("${short.host}")
  private String shortHost;

  public ShortUrlDto getShortUrl(String originUrl) {
    OriginUrl origin = originUrlRepository.findById(originUrl).orElse(null);
    if (!ObjectUtils.isEmpty(origin)) {
      return ShortUrlDto.builder().shortUrl(shortHost + origin.getShortUrl()).build();
    }

    String resultUrl = "";
    while (true) {
      resultUrl = createShortUrl();
      Optional<ShortUrl> byId = shortUrlRepository.findById(resultUrl);
      if (!byId.isPresent()) {
        ShortUrl shortUrl = ShortUrl.builder().id(resultUrl).originUrl(originUrl).build();
        origin = OriginUrl.builder().id(originUrl).shortUrl(resultUrl).build();

        shortUrlRepository.save(shortUrl);
        originUrlRepository.save(origin);
        break;
      }
    }
    return ShortUrlDto.builder().shortUrl(shortHost + resultUrl).build();
  }

  public String getOriginUrl(String shortUrl) {
    String resultUrl = errorPath;
    ShortUrl url = shortUrlRepository.findById(shortUrl).orElse(null);
    if (!ObjectUtils.isEmpty(url)) {
      url.incrementVisitCount();
      shortUrlRepository.save(url);
      resultUrl = url.getOriginUrl();
    }

    return resultUrl;
  }

  private String createShortUrl() {
    return RandomStringUtils.randomAlphanumeric(8);
  }
}
