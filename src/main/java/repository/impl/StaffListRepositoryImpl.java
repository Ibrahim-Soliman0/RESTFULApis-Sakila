package repository.impl;

import entity.StaffList;
import repository.StaffListRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class StaffListRepositoryImpl extends BaseRepositoryImpl<StaffList, Short>
        implements StaffListRepository {

    public StaffListRepositoryImpl() {
        super(StaffList.class);
    }
}