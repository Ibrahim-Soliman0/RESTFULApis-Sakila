package repository.impl;

import entity.Store;
import repository.StoreRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class StoreRepositoryImpl extends BaseRepositoryImpl<Store, Short>
        implements StoreRepository {

    public StoreRepositoryImpl() {
        super(Store.class);
    }
}