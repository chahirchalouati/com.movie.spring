package com.movies.services.Impl;

import com.movies.domain.Genre;
import com.movies.dtos.Requests.CreateGenreRequest;
import com.movies.dtos.Requests.UpdateGenreRequest;
import com.movies.dtos.Responses.GenreResponse;
import com.movies.exceptions.EntityNotFoundException;
import com.movies.mappers.GenreMapper;
import com.movies.repositories.GenreRepository;
import com.movies.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chahir Chalouati
 */
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;
    private final MongoTemplate mongoTemplate;

    private static void notFound() {
        throw new EntityNotFoundException("unable to find genre");
    }

    @Override
    public List<GenreResponse> getGenres(int limit) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "name")).limit(limit);
        final List<Genre> genres = this.mongoTemplate.find(query, Genre.class);
        return this.genreMapper.map(genres);
    }

    @Override
    public GenreResponse save(CreateGenreRequest request) {
        final Genre genre = this.genreMapper.mapToGenre(request);
        final Genre save = this.genreRepository.save(genre);
        return this.genreMapper.mapToGenreResponse(save);
    }

    @Override
    public void delete(String id) {
        this.genreRepository.findById(id).ifPresentOrElse(genreRepository::delete, GenreServiceImpl::notFound);
    }

    @Override
    public GenreResponse findOne(String id) {
        return this.genreRepository.findById(id).map(genreMapper::mapToGenreResponse).orElseThrow(() -> new EntityNotFoundException("unable to find genre with id :" + id));
    }

    @Override
    public GenreResponse update(UpdateGenreRequest request) {
        final Genre genre = this.genreMapper.mapToGenre(request);
        final Genre save = this.genreRepository.save(genre);
        return this.genreMapper.mapToGenreResponse(save);
    }

    @Override
    public void deleteById(String id) {
        this.genreRepository.findById(id).ifPresentOrElse(genre -> genreRepository.deleteById(genre.getId()), GenreServiceImpl::notFound);

    }
}
