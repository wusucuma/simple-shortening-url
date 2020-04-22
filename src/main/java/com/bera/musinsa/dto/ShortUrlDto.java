package com.bera.musinsa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShortUrlDto {

  private String shortUrl;
  private Long visitCount;

  @Builder
  public ShortUrlDto(String shortUrl) {
    this.shortUrl = shortUrl;
    this.visitCount = (long) 0;
  }
}
