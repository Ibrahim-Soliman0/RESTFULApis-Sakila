package repository.impl;

import entity.ActorInfo;
import repository.ActorInfoRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class ActorInfoRepositoryImpl extends BaseRepositoryImpl<ActorInfo, Integer>
        implements ActorInfoRepository {

    public ActorInfoRepositoryImpl() {
        super(ActorInfo.class);
    }
}