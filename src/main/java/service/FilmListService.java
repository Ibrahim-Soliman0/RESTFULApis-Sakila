package service;

import entity.FilmList;
import repository.FilmListRepository;
import repository.impl.FilmListRepositoryImpl;
import service.genaric.BaseService;

public class FilmListService extends BaseService<FilmList, Integer> {

    public FilmListService() {
        this(new FilmListRepositoryImpl());
    }

    public FilmListService(FilmListRepository filmListRepository) {
        super(filmListRepository);
    }

}