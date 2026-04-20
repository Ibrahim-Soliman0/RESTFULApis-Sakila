package mapper;

import dto.SalesByStoreDto;
import entity.SalesByStore;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SalesByStoreMapper {
    SalesByStoreMapper INSTANCE = Mappers.getMapper(SalesByStoreMapper.class);

    SalesByStoreDto toDto(SalesByStore salesByStore);

    SalesByStore toEntity(SalesByStoreDto salesByStoreDto);
}