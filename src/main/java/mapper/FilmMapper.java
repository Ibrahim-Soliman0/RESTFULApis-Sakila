package mapper;

import dto.FilmDto;
import entity.Film;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FilmMapper {
    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    FilmDto toDto(Film film);

    Film toEntity(FilmDto filmDto);
}