package repository.impl;

import entity.Country;
import repository.CountryRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class CountryRepositoryImpl extends BaseRepositoryImpl<Country, Integer>
        implements CountryRepository {

    public CountryRepositoryImpl() {
        super(Country.class);
    }
}