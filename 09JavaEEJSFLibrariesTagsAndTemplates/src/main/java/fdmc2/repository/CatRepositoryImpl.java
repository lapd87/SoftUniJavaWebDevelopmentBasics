package fdmc2.repository;

import fdmc2.domain.entities.Cat;
import fdmc2.repository.interfaces.CatRepository;
import fdmc2.repository.util.EntityDeleter;
import fdmc2.repository.util.EntityGetter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

import static fdmc2.constants.Constants.*;

public class CatRepositoryImpl implements CatRepository {

    private final EntityManager entityManager;

    @Inject
    public CatRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cat save(Cat entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public Optional findAll() {
        return EntityGetter.getEntityByParameter(null,
                ALL_RESULT,
                this.entityManager,
                Cat.class,
                MULTIPLE_RESULT);
    }

    @Override
    public Optional findById(String id) {
        return EntityGetter.getEntityByParameter(id,
                PARAMETER_ID,
                this.entityManager,
                Cat.class,
                SINGLE_RESULT);
    }

    @Override
    public void removeById(String id) {
        EntityDeleter.deleteEntityByParameter(id,
                PARAMETER_ID,
                this.entityManager,
                Cat.class);
    }
}
