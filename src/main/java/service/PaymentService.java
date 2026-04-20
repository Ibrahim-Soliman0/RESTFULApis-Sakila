package service;

import dto.PaymentDto;
import entity.Customer;
import entity.Payment;
import entity.Rental;
import entity.Staff;
import mapper.PaymentMapper;
import repository.PaymentRepository;
import repository.impl.PaymentRepositoryImpl;
import service.genaric.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaymentService extends BaseService<Payment, Integer> {

    private final PaymentMapper paymentMapper = PaymentMapper.INSTANCE;

    public PaymentService() {
        this(new PaymentRepositoryImpl());
    }

    public PaymentService(PaymentRepository paymentRepository) {
        super(paymentRepository);
    }

    public List<PaymentDto> getAllDtos(int page, int size) {
        return getAll(page, size).stream().map(paymentMapper::toDto).collect(Collectors.toList());
    }

    public Optional<PaymentDto> getByIdDto(Integer id) {
        return getById(id).map(paymentMapper::toDto);
    }

    public PaymentDto saveDto(PaymentDto dto) {
        Payment entity = paymentMapper.toEntity(dto);
        // Set relationships
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
        if (dto.getRentalId() != null) {
            Rental rental = new Rental();
            rental.setId(dto.getRentalId());
            entity.setRental(rental);
        }
        Payment saved = save(entity);
        return paymentMapper.toDto(saved);
    }

    public Optional<PaymentDto> updateDto(Integer id, PaymentDto dto) {
        Optional<Payment> existing = getById(id);
        if (existing.isPresent()) {
            Payment entity = paymentMapper.toEntity(dto);
            entity.setId(id);
            // Set relationships
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
            if (dto.getRentalId() != null) {
                Rental rental = new Rental();
                rental.setId(dto.getRentalId());
                entity.setRental(rental);
            }
            Payment updated = save(entity);
            return Optional.of(paymentMapper.toDto(updated));
        }
        return Optional.empty();
    }
}