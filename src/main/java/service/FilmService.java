package service;

import dto.FilmDto;
import entity.Film;
import entity.Language;
import mapper.FilmMapper;
import repository.FilmRepository;
import repository.impl.FilmRepositoryImpl;
import service.genaric.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FilmService extends BaseService<Film, Integer> {

    private final FilmMapper filmMapper = FilmMapper.INSTANCE;

    public FilmService() {
        this(new FilmRepositoryImpl());
    }

    public FilmService(FilmRepository filmRepository) {
        super(filmRepository);
    }

    public List<FilmDto> getAllDtos(int page, int size) {
        return getAll(page, size).stream().map(filmMapper::toDto).collect(Collectors.toList());
    }

    public Optional<FilmDto> getByIdDto(Integer id) {
        return getById(id).map(filmMapper::toDto);
    }

    public FilmDto saveDto(FilmDto dto) {
        Film entity = filmMapper.toEntity(dto);
        // Set relationships
        if (dto.getLanguageId() != null) {
            Language language = new Language();
            language.setId(dto.getLanguageId());
            entity.setLanguage(language);
        }
        if (dto.getOriginalLanguageId() != null) {
            Language originalLanguage = new Language();
            originalLanguage.setId(dto.getOriginalLanguageId());
            entity.setOriginalLanguage(originalLanguage);
        }
        Film saved = save(entity);
        return filmMapper.toDto(saved);
    }

    public Optional<FilmDto> updateDto(Integer id, FilmDto dto) {
        Optional<Film> existing = getById(id);
        if (existing.isPresent()) {
            Film entity = filmMapper.toEntity(dto);
            entity.setId(id);
            // Set relationships
            if (dto.getLanguageId() != null) {
                Language language = new Language();
                language.setId(dto.getLanguageId());
                entity.setLanguage(language);
            }
            if (dto.getOriginalLanguageId() != null) {
                Language originalLanguage = new Language();
                originalLanguage.setId(dto.getOriginalLanguageId());
                entity.setOriginalLanguage(originalLanguage);
            }
            Film updated = save(entity);
            return Optional.of(filmMapper.toDto(updated));
        }
        return Optional.empty();
    }
}