package repository.impl;

import entity.Category;
import repository.CategoryRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category, Short>
        implements CategoryRepository {

    public CategoryRepositoryImpl() {
        super(Category.class);
    }
}