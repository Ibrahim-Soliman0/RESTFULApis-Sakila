package repository.impl;

import entity.Staff;
import repository.StaffRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class StaffRepositoryImpl extends BaseRepositoryImpl<Staff, Short>
        implements StaffRepository {

    public StaffRepositoryImpl() {
        super(Staff.class);
    }
}