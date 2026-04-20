package mapper;

import dto.LanguageDto;
import entity.Language;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LanguageMapper {
    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

    LanguageDto toDto(Language language);

    Language toEntity(LanguageDto dto);
}