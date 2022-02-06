package com.movies.services;

import com.movies.DTOs.Requests.BannerCreateRequest;
import com.movies.domain.Banner;

import java.util.List;

/**
 * @author Chahir Chalouati
 */
public interface BannerService {
    List<Banner> getBanners(int limit);

    Banner save(BannerCreateRequest request);

    void delete(String id);
}
