package service;

import entity.FilmCategory;
import entity.FilmCategoryId;
import repository.FilmCategoryRepository;
import repository.impl.FilmCategoryRepositoryImpl;
import service.genaric.BaseService;

public class FilmCategoryService extends BaseService<FilmCategory, FilmCategoryId> {

    public FilmCategoryService() {
        this(new FilmCategoryRepositoryImpl());
    }

    public FilmCategoryService(FilmCategoryRepository filmCategoryRepository) {
        super(filmCategoryRepository);
    }

}