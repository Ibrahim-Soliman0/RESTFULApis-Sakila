package service;

import entity.StaffList;
import repository.StaffListRepository;
import repository.impl.StaffListRepositoryImpl;
import service.genaric.BaseService;

public class StaffListService extends BaseService<StaffList, Short> {

    public StaffListService() {
        this(new StaffListRepositoryImpl());
    }

    public StaffListService(StaffListRepository staffListRepository) {
        super(staffListRepository);
    }

}