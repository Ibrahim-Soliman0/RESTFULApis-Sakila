package mapper;

import dto.StaffDto;
import entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StaffMapper {
    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);

    StaffDto toDto(Staff staff);

    Staff toEntity(StaffDto staffDto);
}