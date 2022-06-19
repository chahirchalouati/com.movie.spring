package com.movies.services;

import com.movies.domain.Banner;
import com.movies.dtos.Requests.BannerCreateRequest;

import java.util.List;

/**
 * @author Chahir Chalouati
 */
public interface BannerService {
    List<Banner> getBanners(int limit);

    Banner save(BannerCreateRequest request);

    void delete(String id);
}
