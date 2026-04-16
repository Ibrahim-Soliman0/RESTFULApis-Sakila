package repository.impl;

import entity.Actor;
import repository.ActorRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class ActorRepositoryImpl extends BaseRepositoryImpl<Actor>
        implements ActorRepository {

    public ActorRepositoryImpl() {
        super(Actor.class);
    }
}
