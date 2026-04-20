package repository.impl;

import entity.CustomerList;
import repository.CustomerListRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class CustomerListRepositoryImpl extends BaseRepositoryImpl<CustomerList, Integer>
        implements CustomerListRepository {

    public CustomerListRepositoryImpl() {
        super(CustomerList.class);
    }
}