package service;

import dto.StaffDto;
import entity.Address;
import entity.Staff;
import entity.Store;
import mapper.StaffMapper;
import repository.StaffRepository;
import repository.impl.StaffRepositoryImpl;
import service.genaric.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StaffService extends BaseService<Staff, Short> {

    private final StaffMapper staffMapper = StaffMapper.INSTANCE;

    public StaffService() {
        this(new StaffRepositoryImpl());
    }

    public StaffService(StaffRepository staffRepository) {
        super(staffRepository);
    }

    public List<StaffDto> getAllDtos(int page, int size) {
        return getAll(page, size).stream().map(staffMapper::toDto).collect(Collectors.toList());
    }

    public Optional<StaffDto> getByIdDto(Short id) {
        return getById(id).map(staffMapper::toDto);
    }

    public StaffDto saveDto(StaffDto dto) {
        Staff entity = staffMapper.toEntity(dto);
        // Set relationships
        if (dto.getAddressId() != null) {
            Address address = new Address();
            address.setId(dto.getAddressId());
            entity.setAddress(address);
        }
        if (dto.getStoreId() != null) {
            Store store = new Store();
            store.setId(dto.getStoreId());
            entity.setStore(store);
        }
        Staff saved = save(entity);
        return staffMapper.toDto(saved);
    }

    public Optional<StaffDto> updateDto(Short id, StaffDto dto) {
        Optional<Staff> existing = getById(id);
        if (existing.isPresent()) {
            Staff entity = staffMapper.toEntity(dto);
            entity.setId(id);
            // Set relationships
            if (dto.getAddressId() != null) {
                Address address = new Address();
                address.setId(dto.getAddressId());
                entity.setAddress(address);
            }
            if (dto.getStoreId() != null) {
                Store store = new Store();
                store.setId(dto.getStoreId());
                entity.setStore(store);
            }
            Staff updated = save(entity);
            return Optional.of(staffMapper.toDto(updated));
        }
        return Optional.empty();
    }
}