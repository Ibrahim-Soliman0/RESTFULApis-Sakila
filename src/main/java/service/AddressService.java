package service;

import dto.AddressDto;
import entity.Address;
import entity.City;
import mapper.AddressMapper;
import repository.AddressRepository;
import repository.impl.AddressRepositoryImpl;
import service.genaric.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddressService extends BaseService<Address, Integer> {

    private final AddressMapper addressMapper = AddressMapper.INSTANCE;

    public AddressService() {
        this(new AddressRepositoryImpl());
    }

    public AddressService(AddressRepository addressRepository) {
        super(addressRepository);
    }

    public List<AddressDto> getAllDtos(int page, int size) {
        return getAll(page, size).stream().map(addressMapper::toDto).collect(Collectors.toList());
    }

    public Optional<AddressDto> getByIdDto(Integer id) {
        return getById(id).map(addressMapper::toDto);
    }

    public AddressDto saveDto(AddressDto dto) {
        Address entity = addressMapper.toEntity(dto);
        // Set relationships
        if (dto.getCityId() != null) {
            City city = new City();
            city.setId(dto.getCityId());
            entity.setCity(city);
        }
        Address saved = save(entity);
        return addressMapper.toDto(saved);
    }

    public Optional<AddressDto> updateDto(Integer id, AddressDto dto) {
        Optional<Address> existing = getById(id);
        if (existing.isPresent()) {
            Address entity = addressMapper.toEntity(dto);
            entity.setId(id);
            // Set relationships
            if (dto.getCityId() != null) {
                City city = new City();
                city.setId(dto.getCityId());
                entity.setCity(city);
            }
            Address updated = save(entity);
            return Optional.of(addressMapper.toDto(updated));
        }
        return Optional.empty();
    }
}