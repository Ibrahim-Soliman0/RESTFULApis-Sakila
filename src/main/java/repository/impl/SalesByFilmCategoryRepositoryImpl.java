package repository.impl;

import entity.SalesByFilmCategory;
import repository.SalesByFilmCategoryRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class SalesByFilmCategoryRepositoryImpl extends BaseRepositoryImpl<SalesByFilmCategory, String>
        implements SalesByFilmCategoryRepository {

    public SalesByFilmCategoryRepositoryImpl() {
        super(SalesByFilmCategory.class);
    }
}