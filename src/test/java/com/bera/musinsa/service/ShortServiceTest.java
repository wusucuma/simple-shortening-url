package com.bera.musinsa.service;

import com.bera.musinsa.domain.ShortUrl;
import com.bera.musinsa.dto.ShortUrlDto;
import com.bera.musinsa.repository.OriginUrlRepository;
import com.bera.musinsa.repository.ShortUrlRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ShortServiceTest {

  @Autowired private ShortService shortService;
  @Autowired private ShortUrlRepository shortUrlRepository;
  @Autowired private OriginUrlRepository originUrlRepository;

  @Value("${short.host}")
  private String shortHost;

  @Value("${server.error.path}")
  private String errorPath;

  @AfterEach
  public void dataClear() {
    shortUrlRepository.deleteAll();
    originUrlRepository.deleteAll();
  }

  @Test
  public void shorturl_생성_및_같은url_확인() {
    String url = "https://www.naver.com";

    ShortUrlDto shortUrl = shortService.getShortUrl(url);
    ShortUrlDto shortUrl1 = shortService.getShortUrl(url);

    Assertions.assertEquals(shortUrl.getShortUrl(), shortUrl1.getShortUrl());
  }

  @Test
  public void shorturl_입력후_originurl_가져오기() {
    String url = "https://www.naver.com";
    ShortUrlDto shortUrl = shortService.getShortUrl(url);

    String originUrl = shortService.getOriginUrl(shortUrl.getShortUrl().replaceAll(shortHost, ""));
    Assertions.assertEquals(url, originUrl);
  }

  @Test
  public void originurl_가져오면서_visitCount_증가_확인() {
    String url = "https://www.naver.com";
    ShortUrlDto shortUrl = shortService.getShortUrl(url);
    shortService.getOriginUrl(shortUrl.getShortUrl().replaceAll(shortHost, ""));
    Optional<ShortUrl> shortUrl1 = shortUrlRepository.findById(shortUrl.getShortUrl().replaceAll(shortHost, ""));
    Assertions.assertEquals(1, shortUrl1.get().getVisitCount());
  }

  @Test
  public void 등록되지않은_shorturl_입력후_에러url_가져오기() {
    String url = "https://www.naver.com";
    ShortUrlDto shortUrl = shortService.getShortUrl(url);

    String originUrl = shortService.getOriginUrl("1234");
    Assertions.assertEquals(errorPath, originUrl);
  }
}
