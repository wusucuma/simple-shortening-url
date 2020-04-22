package com.bera.musinsa.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@RedisHash("origin")
public class OriginUrl implements Serializable {

  @Id private String id;
  private String shortUrl;

  @Builder
  public OriginUrl(String id, String shortUrl) {
    this.id = id;
    this.shortUrl = shortUrl;
  }
}
