package service;

import dto.StoreDto;
import entity.Address;
import entity.Staff;
import entity.Store;
import mapper.StoreMapper;
import repository.StoreRepository;
import repository.impl.StoreRepositoryImpl;
import service.genaric.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StoreService extends BaseService<Store, Short> {

    private final StoreMapper storeMapper = StoreMapper.INSTANCE;

    public StoreService() {
        this(new StoreRepositoryImpl());
    }

    public StoreService(StoreRepository storeRepository) {
        super(storeRepository);
    }

    public List<StoreDto> getAllDtos(int page, int size) {
        return getAll(page, size).stream().map(storeMapper::toDto).collect(Collectors.toList());
    }

    public Optional<StoreDto> getByIdDto(Short id) {
        return getById(id).map(storeMapper::toDto);
    }

    public StoreDto saveDto(StoreDto dto) {
        Store entity = storeMapper.toEntity(dto);
        // Set relationships
        if (dto.getAddressId() != null) {
            Address address = new Address();
            address.setId(dto.getAddressId());
            entity.setAddress(address);
        }
        if (dto.getManagerStaffId() != null) {
            Staff managerStaff = new Staff();
            managerStaff.setId(dto.getManagerStaffId());
            entity.setManagerStaff(managerStaff);
        }
        Store saved = save(entity);
        return storeMapper.toDto(saved);
    }

    public Optional<StoreDto> updateDto(Short id, StoreDto dto) {
        Optional<Store> existing = getById(id);
        if (existing.isPresent()) {
            Store entity = storeMapper.toEntity(dto);
            entity.setId(id);
            // Set relationships
            if (dto.getAddressId() != null) {
                Address address = new Address();
                address.setId(dto.getAddressId());
                entity.setAddress(address);
            }
            if (dto.getManagerStaffId() != null) {
                Staff managerStaff = new Staff();
                managerStaff.setId(dto.getManagerStaffId());
                entity.setManagerStaff(managerStaff);
            }
            Store updated = save(entity);
            return Optional.of(storeMapper.toDto(updated));
        }
        return Optional.empty();
    }
}