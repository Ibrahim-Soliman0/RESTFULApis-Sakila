package mapper;

import dto.FilmListDto;
import entity.FilmList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FilmListMapper {
    FilmListMapper INSTANCE = Mappers.getMapper(FilmListMapper.class);

    FilmListDto toDto(FilmList filmList);

    FilmList toEntity(FilmListDto filmListDto);
}