package com.bera.musinsa.repository;

import com.bera.musinsa.domain.ShortUrl;
import org.springframework.data.repository.CrudRepository;

public interface ShortUrlRepository extends CrudRepository<ShortUrl, String> {
}
