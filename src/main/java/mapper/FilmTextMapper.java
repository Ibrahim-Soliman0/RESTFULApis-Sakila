package mapper;

import dto.FilmTextDto;
import entity.FilmText;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FilmTextMapper {
    FilmTextMapper INSTANCE = Mappers.getMapper(FilmTextMapper.class);

    FilmTextDto toDto(FilmText filmText);

    FilmText toEntity(FilmTextDto filmTextDto);
}