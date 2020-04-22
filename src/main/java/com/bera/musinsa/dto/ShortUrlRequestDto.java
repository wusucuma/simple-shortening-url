package com.bera.musinsa.dto;

import lombok.Getter;

public class ShortUrlRequestDto {

  @Getter
  public static class CreateShortUrl {
    private String originUrl;
  }
}
