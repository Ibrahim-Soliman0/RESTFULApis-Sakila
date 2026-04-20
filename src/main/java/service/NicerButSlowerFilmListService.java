package service;

import entity.NicerButSlowerFilmList;
import repository.NicerButSlowerFilmListRepository;
import repository.impl.NicerButSlowerFilmListRepositoryImpl;
import service.genaric.BaseService;

public class NicerButSlowerFilmListService extends BaseService<NicerButSlowerFilmList, Integer> {

    public NicerButSlowerFilmListService() {
        this(new NicerButSlowerFilmListRepositoryImpl());
    }

    public NicerButSlowerFilmListService(NicerButSlowerFilmListRepository nicerButSlowerFilmListRepository) {
        super(nicerButSlowerFilmListRepository);
    }

}