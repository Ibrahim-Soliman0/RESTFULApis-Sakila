package service;

import entity.Actor;
import repository.ActorRepository;
import repository.impl.ActorRepositoryImpl;
import service.genaric.BaseService;

public class ActorService extends BaseService<Actor> {

    public ActorService() {
        this(new ActorRepositoryImpl());
    }

    public ActorService(ActorRepository actorRepository) {
        super(actorRepository);
    }


}
