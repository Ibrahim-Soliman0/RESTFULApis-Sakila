package mapper;

import dto.FilmCategoryIdDto;
import entity.FilmCategoryId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FilmCategoryIdMapper {
    FilmCategoryIdMapper INSTANCE = Mappers.getMapper(FilmCategoryIdMapper.class);

    FilmCategoryIdDto toDto(FilmCategoryId filmCategoryId);

    FilmCategoryId toEntity(FilmCategoryIdDto filmCategoryIdDto);
}