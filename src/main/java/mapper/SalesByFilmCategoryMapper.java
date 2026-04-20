package mapper;

import dto.SalesByFilmCategoryDto;
import entity.SalesByFilmCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SalesByFilmCategoryMapper {
    SalesByFilmCategoryMapper INSTANCE = Mappers.getMapper(SalesByFilmCategoryMapper.class);

    SalesByFilmCategoryDto toDto(SalesByFilmCategory salesByFilmCategory);

    SalesByFilmCategory toEntity(SalesByFilmCategoryDto salesByFilmCategoryDto);
}