package service;

import dto.RentalDto;
import entity.Customer;
import entity.Inventory;
import entity.Rental;
import entity.Staff;
import mapper.RentalMapper;
import repository.RentalRepository;
import repository.impl.RentalRepositoryImpl;
import service.genaric.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RentalService extends BaseService<Rental, Integer> {

    private final RentalMapper rentalMapper = RentalMapper.INSTANCE;

    public RentalService() {
        this(new RentalRepositoryImpl());
    }

    public RentalService(RentalRepository rentalRepository) {
        super(rentalRepository);
    }

    public List<RentalDto> getAllDtos(int page, int size) {
        return getAll(page, size).stream().map(rentalMapper::toDto).collect(Collectors.toList());
    }

    public Optional<RentalDto> getByIdDto(Integer id) {
        return getById(id).map(rentalMapper::toDto);
    }

    public RentalDto saveDto(RentalDto dto) {
        Rental entity = rentalMapper.toEntity(dto);
        // Set relationships
        if (dto.getInventoryId() != null) {
            Inventory inventory = new Inventory();
            inventory.setId(dto.getInventoryId());
            entity.setInventory(inventory);
        }
        if (dto.getCustomerId() != null) {
            Customer customer = new Customer();
            customer.setId(dto.getCustomerId());
            entity.setCustomer(customer);
        }
        if (dto.getStaffId() != null) {
            Staff staff = new Staff();
            staff.setId(Short.valueOf(dto.getStaffId().shortValue()));
            entity.setStaff(staff);
        }
        Rental saved = save(entity);
        return rentalMapper.toDto(saved);
    }

    public Optional<RentalDto> updateDto(Integer id, RentalDto dto) {
        Optional<Rental> existing = getById(id);
        if (existing.isPresent()) {
            Rental entity = rentalMapper.toEntity(dto);
            entity.setId(id);
            // Set relationships
            if (dto.getInventoryId() != null) {
                Inventory inventory = new Inventory();
                inventory.setId(dto.getInventoryId());
                entity.setInventory(inventory);
            }
            if (dto.getCustomerId() != null) {
                Customer customer = new Customer();
                customer.setId(dto.getCustomerId());
                entity.setCustomer(customer);
            }
            if (dto.getStaffId() != null) {
                Staff staff = new Staff();
                staff.setId(Short.valueOf(dto.getStaffId().shortValue()));
                entity.setStaff(staff);
            }
            Rental updated = save(entity);
            return Optional.of(rentalMapper.toDto(updated));
        }
        return Optional.empty();
    }
}