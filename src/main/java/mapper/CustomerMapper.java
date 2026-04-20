package mapper;

import dto.CustomerDto;
import entity.Address;
import entity.Customer;
import entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    // Entity → DTO: extract the IDs from the related objects
    @Mapping(source = "store.id", target = "storeId")
    @Mapping(source = "address.id", target = "addressId")
    CustomerDto toDto(Customer customer);

    // DTO → Entity: construct stub objects with just the ID set
    @Mapping(source = "storeId", target = "store")
    @Mapping(source = "addressId", target = "address")
    Customer toEntity(CustomerDto customerDto);

    // Helper: Integer → Store stub (MapStruct picks this up automatically)
    default Store storeFromId(Short id) {
        if (id == null) return null;
        Store store = new Store();
        store.setId(id);
        return store;
    }

    // Helper: Integer → Address stub
    default Address addressFromId(Integer id) {
        if (id == null) return null;
        Address address = new Address();
        address.setId(id);
        return address;
    }
}