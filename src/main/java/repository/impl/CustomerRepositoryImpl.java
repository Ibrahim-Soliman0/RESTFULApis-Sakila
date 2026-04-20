package repository.impl;

import entity.Customer;
import repository.CustomerRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer, Integer>
        implements CustomerRepository {

    public CustomerRepositoryImpl() {
        super(Customer.class);
    }
}