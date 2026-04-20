package repository.impl.genaric;

import jakarta.persistence.EntityManager;
import util.EntityManagerContext;
import repository.Repository;

import java.util.List;

public abstract class BaseRepositoryImpl<T, ID> implements Repository<T, ID> {

    private final Class<T> entityClass;

    protected BaseRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager em() {
        return EntityManagerContext.get();
    }

    @Override
    public T findById(ID id) {
        return em().find(entityClass, id);
    }

    @Override
    public T save(T entity) {
        T managedEntity = em().merge(entity);
        em().flush();
        return managedEntity;
    }

    @Override
    public void delete(T entity) {
        T managedEntity = entity;

        if (!em().contains(entity)) {
            managedEntity = em().merge(entity);
        }

        em().remove(managedEntity);
    }

    @Override
    public List<T> findAll() {
        return em().createQuery(
                "SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    @Override
    public List<T> findAll(int page, int size) {
        return em().createQuery(
                "SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }
}