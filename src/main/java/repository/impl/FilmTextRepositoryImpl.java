package repository.impl;

import entity.FilmText;
import repository.FilmTextRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class FilmTextRepositoryImpl extends BaseRepositoryImpl<FilmText, Integer>
        implements FilmTextRepository {

    public FilmTextRepositoryImpl() {
        super(FilmText.class);
    }
}