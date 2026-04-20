package mapper;

import dto.NicerButSlowerFilmListDto;
import entity.NicerButSlowerFilmList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NicerButSlowerFilmListMapper {
    NicerButSlowerFilmListMapper INSTANCE = Mappers.getMapper(NicerButSlowerFilmListMapper.class);

    NicerButSlowerFilmListDto toDto(NicerButSlowerFilmList nicerButSlowerFilmList);

    NicerButSlowerFilmList toEntity(NicerButSlowerFilmListDto nicerButSlowerFilmListDto);
}