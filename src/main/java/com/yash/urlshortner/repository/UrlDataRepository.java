package com.yash.urlshortner.repository;

import com.yash.urlshortner.model.UrlData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlDataRepository extends JpaRepository<UrlData, Long> {
}
