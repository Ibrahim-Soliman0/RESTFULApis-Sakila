package repository.impl;

import entity.NicerButSlowerFilmList;
import repository.NicerButSlowerFilmListRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class NicerButSlowerFilmListRepositoryImpl extends BaseRepositoryImpl<NicerButSlowerFilmList, Integer>
        implements NicerButSlowerFilmListRepository {

    public NicerButSlowerFilmListRepositoryImpl() {
        super(NicerButSlowerFilmList.class);
    }
}