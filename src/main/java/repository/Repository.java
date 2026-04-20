package repository;

import java.util.List;

public interface Repository<T, ID> {

    T findById(ID id);

    T save(T entity);

    void delete(T entity);

    List<T> findAll();

    List<T> findAll(int page, int size);
}
