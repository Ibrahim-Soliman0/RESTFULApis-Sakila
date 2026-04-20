package repository.impl;

import entity.FilmCategory;
import entity.FilmCategoryId;
import repository.FilmCategoryRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class FilmCategoryRepositoryImpl extends BaseRepositoryImpl<FilmCategory, FilmCategoryId>
        implements FilmCategoryRepository {

    public FilmCategoryRepositoryImpl() {
        super(FilmCategory.class);
    }
}