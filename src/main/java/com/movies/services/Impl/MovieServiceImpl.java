package com.movies.services.Impl;

import com.github.javafaker.Faker;
import com.movies.DTOs.Requests.CreateMovieRequest;
import com.movies.DTOs.Requests.UpdateMovieRequest;
import com.movies.DTOs.Responses.MovieResponse;
import com.movies.domain.File;
import com.movies.domain.Movie;
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
    private final Faker faker;
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
        final List<MovieResponse> movieResponses = this.movieRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(movieMapper::mapToMovieResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(movieResponses, pageable, movieResponses.size());
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
//    @PostConstruct
//    public void initData() {
//        if (this.movieRepository.count() <= 1000L) {
//            for (int i = 0; i < 1000; i++) {
//
//                final Set<Person> actors = new HashSet<>();
//                for (int j = 0; j < RandomUtils.nextInt(5, 100); j++) {
//                    actors.add(Person.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName()).build());
//                }
//
//                final Set<User> users = new HashSet<>();
//                for (int j = 0; j < RandomUtils.nextInt(5, 100); j++) {
//                    User user = new User()
//                            .setUserName(faker.name().username())
//                            .setFirstName(faker.name().firstName())
//                            .setUserName(faker.name().lastName());
//                    users.add(user);
//                }
//
//                final Set<Like> likes = new HashSet<>();
//                for (int j = 0; j < RandomUtils.nextInt(5, 100); j++) {
//                    likes.add(Like.builder().isLiked(RandomUtils.nextBoolean())
//                            .likeCreator(users.stream().findFirst().orElse(null)).build());
//                }
//
//                final Set<Comment> comments = new HashSet<>();
//                for (int j = 0; j < RandomUtils.nextInt(5, 100); j++) {
//                    comments.add(Comment.builder().comment(faker.lorem().paragraph(RandomUtils.nextInt(5, 100)))
//                            .commentCreator(users.stream().findFirst().orElse(null)).build());
//                }
//
//                Movie movie = Movie.builder()
//                        .title(faker.book().title())
//                        .description(faker.lorem().paragraph(RandomUtils.nextInt(5, 100)))
//                        .thumbnails(faker.avatar().image())
//                        .downloadUrl(faker.avatar().image())
//                        .likes(likes)
//                        .actors(actors)
//                        .comments(comments).build();
//                this.movieRepository.save(movie);
//            }
//        }
//    }
}
