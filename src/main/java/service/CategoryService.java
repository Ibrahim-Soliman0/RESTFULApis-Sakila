package service;

import dto.CategoryDto;
import entity.Category;
import mapper.CategoryMapper;
import repository.CategoryRepository;
import repository.impl.CategoryRepositoryImpl;
import service.genaric.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryService extends BaseService<Category, Short> {

    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    public CategoryService() {
        this(new CategoryRepositoryImpl());
    }

    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }

    public List<CategoryDto> getAllDtos(int page, int size) {
        return getAll(page, size).stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDto> getByIdDto(Short id) {
        return getById(id).map(categoryMapper::toDto);
    }

    public CategoryDto saveDto(CategoryDto dto) {
        Category category = categoryMapper.toEntity(dto);
        Category saved = save(category);
        return categoryMapper.toDto(saved);
    }

    public Optional<CategoryDto> updateDto(Short id, CategoryDto dto) {
        Optional<Category> existing = getById(id);
        if (existing.isPresent()) {
            Category entity = categoryMapper.toEntity(dto);
            entity.setId(id);
            Category updated = save(entity);
            return Optional.of(categoryMapper.toDto(updated));
        }
        return Optional.empty();
    }
}