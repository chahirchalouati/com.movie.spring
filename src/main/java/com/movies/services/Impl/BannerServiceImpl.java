package com.movies.services.Impl;

import com.movies.DTOs.Requests.BannerCreateRequest;
import com.movies.domain.Banner;
import com.movies.domain.File;
import com.movies.exceptions.EntityNotFoundException;
import com.movies.repositories.BannerRepository;
import com.movies.repositories.FileRepository;
import com.movies.services.BannerService;
import com.movies.services.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chahir Chalouati
 */
@Service
@AllArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final FileRepository fileRepository;
    private final BannerRepository bannerRepository;
    private final StorageService storageService;
    private final MongoTemplate mongoTemplate;

    @Override
    public List<Banner> getBanners(int limit) {
        Query query = new Query();
        query.with(Sort.by("createdAt").descending()).limit(limit);
        return this.mongoTemplate.find(query, Banner.class);

    }

    @Override
    public Banner save(BannerCreateRequest request) {
        final File storedFile = this.storageService.store(request.getFile());
        final Banner banner = new Banner()
                .setDownloadUrl(storedFile.getDownloadUrl())
                .setTitle(request.getTitle())
                .setMovieRef(storedFile.getName());

        return this.bannerRepository.save(banner);
    }

    @Override
    public void delete(String id) {
        fileRepository.findById(id).ifPresentOrElse(fileRepository::delete, BannerServiceImpl::bannerNotFound);
    }

    private static void bannerNotFound() {
        throw new EntityNotFoundException("banner not found");
    }

    private Criteria filterByType(File.FileType type) {
        return Criteria.where("type").in(type);
    }

}
