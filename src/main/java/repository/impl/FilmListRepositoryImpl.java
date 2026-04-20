package repository.impl;

import entity.FilmList;
import repository.FilmListRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class FilmListRepositoryImpl extends BaseRepositoryImpl<FilmList, Integer>
        implements FilmListRepository {

    public FilmListRepositoryImpl() {
        super(FilmList.class);
    }
}