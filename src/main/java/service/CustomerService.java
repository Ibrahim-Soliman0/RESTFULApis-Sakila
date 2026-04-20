package service;

import dto.CustomerDto;
import entity.Address;
import entity.Customer;
import entity.Store;
import mapper.CustomerMapper;
import repository.CustomerRepository;
import repository.impl.CustomerRepositoryImpl;
import service.genaric.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerService extends BaseService<Customer, Integer> {

    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    public CustomerService() {
        this(new CustomerRepositoryImpl());
    }

    public CustomerService(CustomerRepository customerRepository) {
        super(customerRepository);
    }

    public List<CustomerDto> getAllDtos(int page, int size) {
        return getAll(page, size).stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    public Optional<CustomerDto> getByIdDto(Integer id) {
        return getById(id).map(customerMapper::toDto);
    }

    public CustomerDto saveDto(CustomerDto dto) {
        Customer entity = customerMapper.toEntity(dto);
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
        Customer saved = save(entity);
        return customerMapper.toDto(saved);
    }

    public Optional<CustomerDto> updateDto(Integer id, CustomerDto dto) {
        Optional<Customer> existing = getById(id);
        if (existing.isPresent()) {
            Customer entity = customerMapper.toEntity(dto);
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
            Customer updated = save(entity);
            return Optional.of(customerMapper.toDto(updated));
        }
        return Optional.empty();
    }
}