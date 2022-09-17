package com.fyesilyurt.urlshortener.repository;

import com.fyesilyurt.urlshortener.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    Optional<ShortUrl> findShortUrlByCode(String code);
}
