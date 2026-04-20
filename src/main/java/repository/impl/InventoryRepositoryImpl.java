package repository.impl;

import entity.Inventory;
import repository.InventoryRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class InventoryRepositoryImpl extends BaseRepositoryImpl<Inventory, Integer>
        implements InventoryRepository {

    public InventoryRepositoryImpl() {
        super(Inventory.class);
    }
}