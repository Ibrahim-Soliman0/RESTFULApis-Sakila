package repository.impl;

import entity.Film;
import repository.FilmRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class FilmRepositoryImpl extends BaseRepositoryImpl<Film, Integer>
        implements FilmRepository {

    public FilmRepositoryImpl() {
        super(Film.class);
    }
}