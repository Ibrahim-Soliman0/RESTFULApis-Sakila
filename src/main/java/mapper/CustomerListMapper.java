package mapper;

import dto.CustomerListDto;
import entity.CustomerList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerListMapper {
    CustomerListMapper INSTANCE = Mappers.getMapper(CustomerListMapper.class);

    CustomerListDto toDto(CustomerList customerList);

    CustomerList toEntity(CustomerListDto customerListDto);
}