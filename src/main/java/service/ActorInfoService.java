package service;

import entity.ActorInfo;
import repository.ActorInfoRepository;
import repository.impl.ActorInfoRepositoryImpl;
import service.genaric.BaseService;

public class ActorInfoService extends BaseService<ActorInfo, Integer> {

    public ActorInfoService() {
        this(new ActorInfoRepositoryImpl());
    }

    public ActorInfoService(ActorInfoRepository actorInfoRepository) {
        super(actorInfoRepository);
    }

}