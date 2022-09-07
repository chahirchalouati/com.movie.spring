package com.movies.services.Impl;

import com.movies.domain.File;
import com.movies.domain.Movie;
import com.movies.dtos.Requests.CreateMovieRequest;
import com.movies.dtos.Requests.UpdateMovieRequest;
import com.movies.dtos.Responses.MovieResponse;
import com.movies.exceptions.EntityNotFoundException;
import com.movies.helpers.MovieHelper;
import com.movies.mappers.MovieMapper;
import com.movies.repositories.MovieRepository;
import com.movies.services.MovieService;
import com.movies.services.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Chahir Chalouati
 */
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final MovieHelper movieHelper;
    private final StorageService storageService;

    private static void movieNotFound() {
        throw new EntityNotFoundException("movie not found");
    }

    @Override
    public MovieResponse add(CreateMovieRequest createMovieRequest) {
        final Movie movie = this.movieMapper.mapToMovie(createMovieRequest);
        final File file = this.storageService.store(createMovieRequest.getFile());
        final File thumbnails = this.storageService.store(createMovieRequest.getThumbnails());
        final Integer code = this.movieHelper.createCode();

        movie.setThumbnails(thumbnails.getDownloadUrl());
        movie.setCode(code);
        movie.setDownloadUrl(file.getDownloadUrl());
        movie.setPath(file.getPath());

        final Movie storedMovie = movieRepository.save(movie);
        return this.movieMapper.mapToMovieResponse(storedMovie);
    }

    @Override
    public MovieResponse getOne(String id) {
        return this.movieMapper.mapToMovieResponse(this.movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("movie not found")));
    }

    @Override
    public MovieResponse update(UpdateMovieRequest updateMovieRequest) {
        return this.movieRepository.findById(updateMovieRequest.getId()).map(handleMovieUpdate(updateMovieRequest))
                .map(this.movieMapper::mapToMovieResponse)
                .orElseThrow(() -> new EntityNotFoundException("movie not found"));

    }

    @Override
    public boolean deleteById(String id) {
        this.movieRepository.findById(id).ifPresentOrElse(this.movieRepository::delete, MovieServiceImpl::movieNotFound);
        return true;
    }

    @Override
    public boolean deleteByTitle(String title) {
        this.movieRepository.findByTitle(title).ifPresentOrElse(this.movieRepository::delete, MovieServiceImpl::movieNotFound);
        return true;
    }

    @Override
    public Page<MovieResponse> findAll(Pageable pageable) {
        final Page<Movie> page = this.movieRepository.findAll(pageable);
        final List<MovieResponse> movieResponses = page
                .getContent()
                .stream()
                .map(movieMapper::mapToMovieResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(movieResponses, pageable, page.getTotalElements());
    }

    @Override
    public MovieResponse getByCode(Integer code) {
        return this.movieMapper.mapToMovieResponse(this.movieRepository.findByCode(code).orElseThrow(() -> new EntityNotFoundException("movie not found")));
    }

    private Function<Movie, Movie> handleMovieUpdate(UpdateMovieRequest updateMovieRequest) {
        return movie -> {
            final File file = this.storageService.store(updateMovieRequest.getFile());
            final File thumbnails = this.storageService.store(updateMovieRequest.getThumbnails());
            movie.setThumbnails(thumbnails.getDownloadUrl());
            movie.setDownloadUrl(file.getDownloadUrl());
            movie.setPath(file.getPath());
            movie.setDescription(updateMovieRequest.getDescription());
            movie.setTitle(updateMovieRequest.getTitle());
            movie.setActors(updateMovieRequest.getActors());
            movie.setLikes(updateMovieRequest.getLikes());
            movie.setComments(updateMovieRequest.getComments());
            return this.movieRepository.save(movie);
        };
    }
}
