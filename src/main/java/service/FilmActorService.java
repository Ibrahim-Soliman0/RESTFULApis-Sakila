package service;

import entity.FilmActor;
import entity.FilmActorId;
import repository.FilmActorRepository;
import repository.impl.FilmActorRepositoryImpl;
import service.genaric.BaseService;

public class FilmActorService extends BaseService<FilmActor, FilmActorId> {

    public FilmActorService() {
        this(new FilmActorRepositoryImpl());
    }

    public FilmActorService(FilmActorRepository filmActorRepository) {
        super(filmActorRepository);
    }

}