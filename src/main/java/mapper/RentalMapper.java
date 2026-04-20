package mapper;

import dto.RentalDto;
import entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RentalMapper {
    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);

    RentalDto toDto(Rental rental);

    Rental toEntity(RentalDto rentalDto);
}