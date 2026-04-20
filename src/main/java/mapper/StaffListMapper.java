package mapper;

import dto.StaffListDto;
import entity.StaffList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StaffListMapper {
    StaffListMapper INSTANCE = Mappers.getMapper(StaffListMapper.class);

    StaffListDto toDto(StaffList staffList);

    StaffList toEntity(StaffListDto staffListDto);
}