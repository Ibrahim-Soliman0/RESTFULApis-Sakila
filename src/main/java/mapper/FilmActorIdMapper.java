package mapper;

import dto.FilmActorIdDto;
import entity.FilmActorId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FilmActorIdMapper {
    FilmActorIdMapper INSTANCE = Mappers.getMapper(FilmActorIdMapper.class);

    FilmActorIdDto toDto(FilmActorId filmActorId);

    FilmActorId toEntity(FilmActorIdDto filmActorIdDto);
}