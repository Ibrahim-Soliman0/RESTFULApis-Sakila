package service;

import entity.SalesByStore;
import repository.SalesByStoreRepository;
import repository.impl.SalesByStoreRepositoryImpl;
import service.genaric.BaseService;

public class SalesByStoreService extends BaseService<SalesByStore, String> {

    public SalesByStoreService() {
        this(new SalesByStoreRepositoryImpl());
    }

    public SalesByStoreService(SalesByStoreRepository salesByStoreRepository) {
        super(salesByStoreRepository);
    }

}