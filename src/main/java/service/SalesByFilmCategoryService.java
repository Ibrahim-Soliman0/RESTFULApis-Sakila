package service;

import entity.SalesByFilmCategory;
import repository.SalesByFilmCategoryRepository;
import repository.impl.SalesByFilmCategoryRepositoryImpl;
import service.genaric.BaseService;

public class SalesByFilmCategoryService extends BaseService<SalesByFilmCategory, String> {

    public SalesByFilmCategoryService() {
        this(new SalesByFilmCategoryRepositoryImpl());
    }

    public SalesByFilmCategoryService(SalesByFilmCategoryRepository salesByFilmCategoryRepository) {
        super(salesByFilmCategoryRepository);
    }

}