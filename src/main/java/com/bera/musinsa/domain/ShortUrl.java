package com.bera.musinsa.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@RedisHash("short")
public class ShortUrl implements Serializable {

  @Id private String id;
  private String originUrl;
  private Long visitCount;

  @Builder
  public ShortUrl(String id, String originUrl) {
    this.id = id;
    this.originUrl = originUrl;
    this.visitCount = (long) 0;
  }

  public void incrementVisitCount() {
    this.visitCount += 1;
  }
}
