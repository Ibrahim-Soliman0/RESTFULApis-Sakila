package repository.impl;

import entity.Payment;
import repository.PaymentRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class PaymentRepositoryImpl extends BaseRepositoryImpl<Payment, Integer>
        implements PaymentRepository {

    public PaymentRepositoryImpl() {
        super(Payment.class);
    }
}