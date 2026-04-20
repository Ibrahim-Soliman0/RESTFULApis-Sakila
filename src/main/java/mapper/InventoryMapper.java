package mapper;

import dto.InventoryDto;
import entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    InventoryDto toDto(Inventory inventory);

    Inventory toEntity(InventoryDto inventoryDto);
}