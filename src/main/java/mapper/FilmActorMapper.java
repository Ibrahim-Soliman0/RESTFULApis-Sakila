package mapper;

import dto.FilmActorDto;
import entity.FilmActor;
import entity.FilmActorId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FilmActorMapper {
    FilmActorMapper INSTANCE = Mappers.getMapper(FilmActorMapper.class);

    FilmActorDto toDto(FilmActor filmActor);

    FilmActor toEntity(FilmActorDto filmActorDto);
}