package service;

import dto.CountryDto;
import entity.Country;
import mapper.CountryMapper;
import repository.CountryRepository;
import repository.impl.CountryRepositoryImpl;
import service.genaric.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CountryService extends BaseService<Country, Integer> {

    private final CountryMapper countryMapper = CountryMapper.INSTANCE;

    public CountryService() {
        this(new CountryRepositoryImpl());
    }

    public CountryService(CountryRepository countryRepository) {
        super(countryRepository);
    }

    public List<CountryDto> getAllDtos(int page, int size) {
        return getAll(page, size).stream()
                .map(countryMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<CountryDto> getByIdDto(Integer id) {
        return getById(id).map(countryMapper::toDto);
    }

    public CountryDto saveDto(CountryDto dto) {
        Country country = countryMapper.toEntity(dto);
        Country saved = save(country);
        return countryMapper.toDto(saved);
    }

    public Optional<CountryDto> updateDto(Integer id, CountryDto dto) {
        Optional<Country> existing = getById(id);
        if (existing.isPresent()) {
            Country entity = countryMapper.toEntity(dto);
            entity.setId(id);
            Country updated = save(entity);
            return Optional.of(countryMapper.toDto(updated));
        }
        return Optional.empty();
    }
}