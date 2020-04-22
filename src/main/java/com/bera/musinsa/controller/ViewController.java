package com.bera.musinsa.controller;

import com.bera.musinsa.service.ShortService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ViewController {

  private final ShortService shortService;

  @GetMapping("/{short}")
  public String redirectController(@PathVariable("short") String shortUrl) {
    String originUrl = shortService.getOriginUrl(shortUrl);
    return "redirect:" + originUrl;
  }
}
