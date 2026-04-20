package mapper;

import dto.FilmCategoryDto;
import entity.FilmCategory;
import entity.FilmCategoryId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FilmCategoryMapper {
    FilmCategoryMapper INSTANCE = Mappers.getMapper(FilmCategoryMapper.class);

    FilmCategoryDto toDto(FilmCategory filmCategory);

    FilmCategory toEntity(FilmCategoryDto filmCategoryDto);
}