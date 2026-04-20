package mapper;

import dto.StoreDto;
import entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    StoreDto toDto(Store store);

    Store toEntity(StoreDto storeDto);
}