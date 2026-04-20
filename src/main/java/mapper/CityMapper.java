package mapper;

import dto.CityDto;
import entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDto toDto(City city);

    City toEntity(CityDto cityDto);
}