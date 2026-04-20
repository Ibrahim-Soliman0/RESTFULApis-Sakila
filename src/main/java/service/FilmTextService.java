package service;

import entity.FilmText;
import repository.FilmTextRepository;
import repository.impl.FilmTextRepositoryImpl;
import service.genaric.BaseService;

public class FilmTextService extends BaseService<FilmText, Integer> {

    public FilmTextService() {
        this(new FilmTextRepositoryImpl());
    }

    public FilmTextService(FilmTextRepository filmTextRepository) {
        super(filmTextRepository);
    }

}