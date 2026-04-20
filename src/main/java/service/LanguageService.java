package service;

import dto.LanguageDto;
import entity.Language;
import mapper.LanguageMapper;
import repository.LanguageRepository;
import repository.impl.LanguageRepositoryImpl;
import service.genaric.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LanguageService extends BaseService<Language, Short> {

    private final LanguageMapper languageMapper = LanguageMapper.INSTANCE;

    public LanguageService() {
        this(new LanguageRepositoryImpl());
    }

    public LanguageService(LanguageRepository languageRepository) {
        super(languageRepository);
    }

    public List<LanguageDto> getAllDtos(int page, int size) {
        return getAll(page, size).stream()
                .map(languageMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<LanguageDto> getByIdDto(Short id) {
        return getById(id).map(languageMapper::toDto);
    }

    public LanguageDto saveDto(LanguageDto dto) {
        Language language = languageMapper.toEntity(dto);
        Language saved = save(language);
        return languageMapper.toDto(saved);
    }

    public Optional<LanguageDto> updateDto(Short id, LanguageDto dto) {
        Optional<Language> existing = getById(id);
        if (existing.isPresent()) {
            Language entity = languageMapper.toEntity(dto);
            entity.setId(id);
            Language updated = save(entity);
            return Optional.of(languageMapper.toDto(updated));
        }
        return Optional.empty();
    }
}