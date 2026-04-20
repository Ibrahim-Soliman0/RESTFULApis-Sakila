package service;

import dto.CityDto;
import entity.City;
import entity.Country;
import mapper.CityMapper;
import repository.CityRepository;
import repository.impl.CityRepositoryImpl;
import service.genaric.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CityService extends BaseService<City, Integer> {

    private final CityMapper cityMapper = CityMapper.INSTANCE;

    public CityService() {
        this(new CityRepositoryImpl());
    }

    public CityService(CityRepository cityRepository) {
        super(cityRepository);
    }

    public List<CityDto> getAllDtos(int page, int size) {
        return getAll(page, size).stream().map(cityMapper::toDto).collect(Collectors.toList());
    }

    public Optional<CityDto> getByIdDto(Integer id) {
        return getById(id).map(cityMapper::toDto);
    }

    public CityDto saveDto(CityDto dto) {
        City entity = cityMapper.toEntity(dto);
        // Set relationships
        if (dto.getCountryId() != null) {
            Country country = new Country();
            country.setId(dto.getCountryId());
            entity.setCountry(country);
        }
        City saved = save(entity);
        return cityMapper.toDto(saved);
    }

    public Optional<CityDto> updateDto(Integer id, CityDto dto) {
        Optional<City> existing = getById(id);
        if (existing.isPresent()) {
            City entity = cityMapper.toEntity(dto);
            entity.setId(id);
            // Set relationships
            if (dto.getCountryId() != null) {
                Country country = new Country();
                country.setId(dto.getCountryId());
                entity.setCountry(country);
            }
            City updated = save(entity);
            return Optional.of(cityMapper.toDto(updated));
        }
        return Optional.empty();
    }
}