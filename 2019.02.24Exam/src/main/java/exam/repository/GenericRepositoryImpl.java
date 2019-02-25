package exam.repository;

import exam.repository.interfaces.GenericRepository;
import exam.repository.util.EntityDeleter;
import exam.repository.util.EntityGetter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static exam.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 21.2.2019 г.
 * Time: 15:23 ч.
 */
public abstract class GenericRepositoryImpl<E, ID> implements GenericRepository<E, ID> {

    private final EntityManager entityManager;
    private final Class<E> aClass;

    @Inject
    public GenericRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.aClass = this.getGenericTypeClass();
    }

    @Override
    public E save(E entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public E update(E entity) {
        this.entityManager.getTransaction().begin();
        E updated = this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();

        return updated;
    }

    @Override
    public int deleteById(ID id) {

        return EntityDeleter.deleteEntityByParameter(id,
                PARAMETER_ID,
                this.entityManager,
                this.aClass);
    }

    @Override
    public Optional findAll() {
        return EntityGetter.getEntityByParameter(null,
                ALL_RESULT,
                this.entityManager,
                this.aClass,
                MULTIPLE_RESULT);
    }

    @Override
    public Optional findById(ID id) {
        return EntityGetter.getEntityByParameter(id,
                PARAMETER_ID,
                this.entityManager,
                this.aClass,
                SINGLE_RESULT);
    }

    @SuppressWarnings(WARNINGS_UNCHECKED)
    @Override
    public int size() {
        return ((List<E>) this.findAll()
                .orElseGet(ArrayList::new))
                .size();
    }

    @SuppressWarnings(WARNINGS_UNCHECKED)
    private Class<E> getGenericTypeClass() {
        try {
            String className = ((ParameterizedType) getClass()
                    .getGenericSuperclass())
                    .getActualTypeArguments()[0]
                    .getTypeName();
            Class<?> clazz = Class.forName(className);
            return (Class<E>) clazz;
        } catch (Exception e) {
            throw new IllegalStateException("Class is not parametrized with generic type!!! Please use extends <> ");
        }
    }
}