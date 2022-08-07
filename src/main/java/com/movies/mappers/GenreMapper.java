package com.movies.mappers;

import com.movies.domain.Genre;
import com.movies.dtos.Requests.CreateGenreRequest;
import com.movies.dtos.Requests.UpdateGenreRequest;
import com.movies.dtos.Responses.GenreResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collection;
import java.util.List;

/**
 * @author Chahir Chalouati
 */
@Mapper(componentModel = "spring")
public abstract class GenreMapper {

    @Mappings({
            @Mapping(source = "id", target = "id", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
            @Mapping(source = "name", target = "name"),
    })
    public abstract GenreResponse mapToGenreResponse(Genre genre);

    @Mappings({
            @Mapping(source = "name", target = "name"),
    })
    public abstract Genre mapToGenre(CreateGenreRequest request);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
    })
    public abstract Genre mapToGenre(UpdateGenreRequest request);

    public abstract List<GenreResponse> map(Collection<Genre> genres);


}
