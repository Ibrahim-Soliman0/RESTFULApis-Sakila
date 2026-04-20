package service;

import entity.CustomerList;
import repository.CustomerListRepository;
import repository.impl.CustomerListRepositoryImpl;
import service.genaric.BaseService;

public class CustomerListService extends BaseService<CustomerList, Integer> {

    public CustomerListService() {
        this(new CustomerListRepositoryImpl());
    }

    public CustomerListService(CustomerListRepository customerListRepository) {
        super(customerListRepository);
    }

}