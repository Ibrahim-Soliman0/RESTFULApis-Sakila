package mapper;

import dto.ActorInfoDto;
import entity.ActorInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActorInfoMapper {
    ActorInfoMapper INSTANCE = Mappers.getMapper(ActorInfoMapper.class);

    ActorInfoDto toDto(ActorInfo actorInfo);

    ActorInfo toEntity(ActorInfoDto actorInfoDto);
}