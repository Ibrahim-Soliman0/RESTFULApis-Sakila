package repository.impl;

import entity.SalesByStore;
import repository.SalesByStoreRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class SalesByStoreRepositoryImpl extends BaseRepositoryImpl<SalesByStore, String>
        implements SalesByStoreRepository {

    public SalesByStoreRepositoryImpl() {
        super(SalesByStore.class);
    }
}