package repository.impl;

import entity.FilmActor;
import entity.FilmActorId;
import repository.FilmActorRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class FilmActorRepositoryImpl extends BaseRepositoryImpl<FilmActor, FilmActorId>
        implements FilmActorRepository {

    public FilmActorRepositoryImpl() {
        super(FilmActor.class);
    }
}