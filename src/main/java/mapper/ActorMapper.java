package mapper;

import dto.ActorDto;
import entity.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActorMapper {

    ActorMapper INSTANCE = Mappers.getMapper(ActorMapper.class);

    ActorDto toDto(Actor actor);
    Actor toEntity(ActorDto dto);
}