package repository.impl;

import entity.Address;
import repository.AddressRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class AddressRepositoryImpl extends BaseRepositoryImpl<Address, Integer>
        implements AddressRepository {

    public AddressRepositoryImpl() {
        super(Address.class);
    }
}