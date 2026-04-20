package service.genaric;

import repository.Repository;

import java.util.List;

import java.util.Optional;

public abstract class BaseService<T, ID> {

    protected final Repository<T, ID> repository;

    protected BaseService(Repository<T, ID> repository) {
        this.repository = repository;
    }

    public Optional<T> getById(ID id) {
        return Optional.ofNullable(repository.findById(id));
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public List<T> getAll(int page, int size) {
        return repository.findAll(page, size);
    }
}