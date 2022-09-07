package com.movies.migrations;

import com.github.javafaker.Faker;
import com.movies.domain.Comment;
import com.movies.domain.Like;
import com.movies.domain.Movie;
import com.movies.domain.User;
import com.movies.repositories.MovieRepository;
import com.movies.repositories.RoleRepository;
import com.movies.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.movies.utils.constants.Constants.DEFAULT_GENRES;
import static org.apache.commons.lang3.RandomUtils.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component("movieMigrator")
@DependsOn({"roleMigrator", "userMigrator"})
@RequiredArgsConstructor
@Slf4j
public class MovieMigrationImpl implements Migration {
    private final Faker faker;
    private final MovieRepository movieRepository;
    private final RoleRepository roleRepository;

    @Override
    public void migrate() {
        log.info("START MIGRATION ON " + this.getClass().getName());
        IntStream.range(0, 100)
                .mapToObj(index -> Movie.builder()
                        .code(nextInt())
                        .description(faker.lorem().sentence(nextInt(1, 1000)))
                        .duration(Duration.ofMinutes(nextLong(50L, 300L)))
                        .inCinema(DateUtils.generateLocalDateTime())
                        .isExclusive(nextBoolean())
                        .thumbnails(faker.internet().image())
                        .createdAt(DateUtils.generateLocalDateTime())
                        .genre(DEFAULT_GENRES.get(nextInt(0, DEFAULT_GENRES.size())))
                        .downloadUrl(faker.internet().image())
                        .path(EMPTY)
                        .title(faker.lorem().sentence(nextInt(2, 4)))
                        .actors(getActors())
                        .comments(getComments())
                        .likes(getLikes())
                        .build())
                .peek(movie -> log.info("movie code " + movie.getCode()))
                .forEach(movieRepository::save);
        log.info("END MIGRATION ON " + this.getClass().getName());
    }

    private Set<Like> getLikes() {
        return IntStream.range(0, nextInt(1, 50))
                .mapToObj(value -> Like.builder()
                        .isLiked(nextBoolean())
                        .likeCreator(getRandomUser())
                        .createdAt(DateUtils.generateLocalDateTime())
                        .build())
                .collect(Collectors.toSet());
    }

    private Set<User> getActors() {
        return IntStream.range(0, nextInt(1, 50))
                .mapToObj(index -> getRandomUser())
                .collect(Collectors.toUnmodifiableSet());
    }

    private User getRandomUser() {
        return User.builder().lastName(faker.name().lastName())
                .password("secret123")
                .createdAt(DateUtils.generateLocalDateTime())
                .firstName(faker.name().firstName())
                .avatar("https://i.pravatar.cc/150")
                .roles(new HashSet<>(roleRepository.findByRoleContainingIgnoreCase("user")))
                .build();
    }

    private Set<Comment> getComments() {
        return IntStream.range(0, nextInt(1, 50))
                .mapToObj(index -> Comment.builder()
                        .comment(faker.lorem().sentence(1, nextInt(1, 100)))
                        .createdAt(DateUtils.generateLocalDateTime())
                        .commentCreator(getRandomUser())
                        .build()).collect(Collectors.toUnmodifiableSet());
    }
}
