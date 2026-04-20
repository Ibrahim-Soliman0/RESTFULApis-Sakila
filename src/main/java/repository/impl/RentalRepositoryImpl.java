package repository.impl;

import entity.Rental;
import repository.RentalRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class RentalRepositoryImpl extends BaseRepositoryImpl<Rental, Integer>
        implements RentalRepository {

    public RentalRepositoryImpl() {
        super(Rental.class);
    }
}