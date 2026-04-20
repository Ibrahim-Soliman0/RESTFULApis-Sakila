package repository.impl;

import entity.City;
import repository.CityRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class CityRepositoryImpl extends BaseRepositoryImpl<City, Integer>
        implements CityRepository {

    public CityRepositoryImpl() {
        super(City.class);
    }
}